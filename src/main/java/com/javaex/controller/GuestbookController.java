package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@GetMapping("/api/guests")
	public List<GuestVo> getList() {

		List<GuestVo> guestList = guestbookService.exeGuestList();

		return guestList;
	}

	@PostMapping("/api/guests")
	public int addGuest(@RequestBody GuestVo guestVo) {

		int count = guestbookService.exeWrite(guestVo);

		return count;
	}

	@DeleteMapping("/api/guests/{no}")
	public JsonResult delPerson(@PathVariable(value = "no") int no) {
		int count = guestbookService.exeDelete(no, null);

		if (count != 1) {
			return JsonResult.fail("해당번호가 없습니다");
		} else {
			return JsonResult.success(count);
		}
	}

}
