package com.sachin.arapp.data

import com.sachin.arapp.R
import jakarta.inject.Inject

interface DrillRepository {
    fun getDrills(): List<Drill>
}

class DrillRepositoryImpl @Inject constructor() : DrillRepository {
    override fun getDrills(): List<Drill> = listOf(
        Drill(
            id = 1,
            name = "Drill 1",
            description = "A high-speed steel drill used for basic metal drilling tasks.",
            tips = "Standard conical tip, 118° angle for general-purpose cutting.",
            imageRes = R.drawable.ic_drill1,
            modelFileName = "drill1.glb"
        ),
        Drill(
            id = 2,
            name = "Drill 2",
            description = "A carbide-tipped drill designed for hard surfaces like masonry.",
            tips = "Carbide chisel tip with split-point design for better grip on hard materials.",
            imageRes = R.drawable.ic_drill1,
            modelFileName = "drill2.glb"
        ),
        Drill(
            id = 3,
            name = "Drill 3",
            description = "A twist drill suitable for wood and soft materials.",
            tips = "Brad point tip for precise starting and reduced wandering on wood.",
            imageRes = R.drawable.ic_drill3,
            modelFileName = "drill3.glb"
        )

    )

}