package com.kavrin.borutoapp.domain.use_cases.save_onboarding

import com.kavrin.borutoapp.data.repository.Repository

class SaveOnBoardingUseCase(
	private val repository: Repository
) {
	// To call this function immediately without call invoke explicitly. Call our class like function
	suspend operator fun invoke(completed: Boolean) {
		repository.saveOnBoardingState(completed = completed)
	}
}