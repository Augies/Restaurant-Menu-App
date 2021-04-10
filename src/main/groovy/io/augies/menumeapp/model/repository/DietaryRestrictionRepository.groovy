package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.DietaryRestriction
import org.springframework.data.jpa.repository.JpaRepository

interface DietaryRestrictionRepository extends JpaRepository<DietaryRestriction, Long> {
}