package com.sachin.arapp.ui.screens.ar

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.ar.core.Plane
import com.google.android.filament.*
import io.github.sceneview.ar.ARSceneView
import io.github.sceneview.math.Position
import io.github.sceneview.node.LightNode
import io.github.sceneview.node.ModelNode
import kotlinx.coroutines.launch

private const val TAG = "ArScreen"

@SuppressLint("ClickableViewAccessibility")
@Composable
fun ArScreen(modelFileName: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        // This holds the currently placed 3D model on screen
        var placedNode by remember { mutableStateOf<ModelNode?>(null) }

        // This is the coroutine scope used for background work like loading the model
        val court = rememberCoroutineScope()

        // Native Android AR view inside Compose
        AndroidView(
            factory = { context ->
                val arSceneView = ARSceneView(context).apply {

                    // ----- LIGHTING SETUP -----

                    // Add soft ambient (indirect) light to the scene
                    scene.indirectLight = IndirectLight.Builder()
                        .intensity(30_000f) // Adjust brightness
                        .build(engine)

                    // Add a white directional light from a specific direction (sunlight-like)
                    val lightEntity = EntityManager.get().create()
                    LightManager.Builder(LightManager.Type.DIRECTIONAL)
                        .color(1.0f, 1.0f, 1.0f) // RGB white
                        .intensity(50_000f) // Stronger light
                        .direction(0.5f, -1.0f, -0.5f) // Direction of light
                        .build(engine, lightEntity)

                    // Wrap the light into a node and place it slightly above
                    val lightNode = LightNode(
                        engine = engine,
                        entity = lightEntity
                    ).apply {
                        position = Position(0f, 2f, 0f) // Light is 2 meters above the scene
                    }
                    addChildNode(lightNode)
                }

                // ----- TOUCH HANDLING TO PLACE MODEL -----
                arSceneView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        val session = arSceneView.session
                        if (session == null) {
                            Log.w(TAG, "AR Session is null")
                            return@setOnTouchListener false
                        }

                        val frame = session.frame
                        if (frame == null) {
                            Log.w(TAG, "Current AR frame is null")
                            return@setOnTouchListener false
                        }

                        // Do hit testing to find planes in camera view
                        val hitResultList = frame.hitTest(event)
                        val hit = hitResultList.firstOrNull { result ->
                            val trackable = result.trackable
                            // Only use hits that landed inside detected horizontal plane
                            trackable is Plane && trackable.isPoseInPolygon(result.hitPose)
                        }

                        if (hit != null) {
                            // Launch coroutine to load and place model
                            court.launch {
                                try {
                                    // Load the 3D model from assets (e.g., drill.glb)
                                    val modelInstance = arSceneView.modelLoader.loadModelInstance(modelFileName)
                                        ?: throw IllegalStateException("Failed to load model instance")

                                    // Create a ModelNode (3D object container)
                                    val modelNode = ModelNode(
                                        modelInstance = modelInstance,
                                        scaleToUnits = 0.3f, // Resize model
                                        autoAnimate = true   // Start animation if any
                                    ).apply {
                                        // Set position of model to where user tapped
                                        position = Position(
                                            hit.hitPose.tx(),
                                            hit.hitPose.ty(),
                                            hit.hitPose.tz()
                                        )
                                    }

                                    // Remove previous model if it exists
                                    placedNode?.let { arSceneView.removeChildNode(it) }

                                    // Add the new model to the scene
                                    arSceneView.addChildNode(modelNode)
                                    placedNode = modelNode
                                } catch (e: Exception) {
                                    Log.e(TAG, "Error loading model", e)
                                }
                            }
                        }
                    }
                    true
                }

                // Return the AR scene view to AndroidView
                arSceneView
            },
            modifier = Modifier.fillMaxSize()
        )

        // Instruction text at the top of the screen
        Text(
            text = "Tap on ground to place drill marker",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            color = ComposeColor.White,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}
