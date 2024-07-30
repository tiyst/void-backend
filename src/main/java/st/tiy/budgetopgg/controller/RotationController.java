package st.tiy.budgetopgg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.rotation.Rotation;
import st.tiy.budgetopgg.service.RotationService;

@RestController
@RequestMapping("/api/rotation")
public class RotationController {

	private final RotationService rotationService;

	public RotationController(RotationService rotationService) {
		this.rotationService = rotationService;
	}

	@GetMapping
	public ResponseEntity<Rotation> getCurrentWeekRotation() {
		return ResponseEntity.ok().body(rotationService.getCurrentWeekRotation());
	}

}
