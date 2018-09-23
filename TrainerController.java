package com.yash.ota.controller;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.ota.command.BatchCreateCommand;
import com.yash.ota.command.BatchEditCommand;
import com.yash.ota.command.EditTraineeDetailCommand;
import com.yash.ota.command.ImportQuestionCommand;
import com.yash.ota.command.QuestionCreateCommand;
import com.yash.ota.command.TestCreateCommand;
import com.yash.ota.command.TestEditCommand;
import com.yash.ota.domain.Batch;
import com.yash.ota.domain.Question;
import com.yash.ota.domain.ReportItem;
import com.yash.ota.domain.ResultDetail;
import com.yash.ota.domain.Technology;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateEntryException;
import com.yash.ota.exception.EmptyFieldException;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.service.BatchService;
import com.yash.ota.service.QuestionService;
import com.yash.ota.service.ResultDetailService;
import com.yash.ota.service.ResultService;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.service.TestService;
import com.yash.ota.service.TopicService;
import com.yash.ota.service.UserService;
import com.yash.ota.util.CommonUtil;

@Controller
@RequestMapping("/trainer")
public class TrainerController {



	@Autowired
	private TestService testService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private BatchService batchService;
	

	@Autowired
	private UserService userService;

	private Logger log = Logger.getLogger(TrainerController.class);


	/**
	 *
	 * @param batch_id
	 * @param model
	 * @param session
	 * @param batchEditCommand
	 * @return String url
	 * @author amit.kolluri
	 */
	@RequestMapping(value = "/batch/{batchid}/edit.htm", method = RequestMethod.GET)
	public String showEditBatchPage(@PathVariable("batchid") int batch_id, Model model, HttpSession session,
			@ModelAttribute("command") BatchEditCommand batchEditCommand) throws InvalidIdException {

		int batchId = batch_id;
		Batch batch = batchService.getBatchById(batchId);
		session.setAttribute("batchId", batch.getId());
		model.addAttribute("batchName", batch.getName());
		return "trainer/batch/edit-batch";
	}

	/**
	 * @param request
	 * @param batchEditCommand
	 * @return
	 * @author Amit.kolluri //TODO:UPDATE BATCH WITH ID PASSED THROUGH URL NOT
	 *         SESSION
	 */
	@RequestMapping(value = "/editbatch.htm", method = RequestMethod.POST)
	public String processUpdateBatch(HttpServletRequest request,
			@ModelAttribute("command") BatchEditCommand batchEditCommand) throws InvalidIdException {

		int batchId = (Integer) request.getSession().getAttribute("batchId");
		Batch batch = batchService.getBatchById(batchId);
		batch.setName(batchEditCommand.getNewBatchName());

		batchService.updateBatch(batch);

		return "trainer/batch/dashboard";
	}

	
	/**
	 * process the trainee approvals, change the status of trainees to active once trainer approves
	 *
	 * @param model
	 * @param request
	 * @return trainer dashboard after trainer approves the trainees
	 * @author amit kolluri
	 */
	@RequestMapping(value = "/processapprovedrequests.htm", method = RequestMethod.POST)
	public String processTraineeApprovals(Model model, HttpServletRequest request) {

		String[] status = request.getParameterValues("status");


		int[] approvedUserIds = new int[status.length];
		for (int i = 0; i < status.length; i++) {
			approvedUserIds[i] = Integer.parseInt(status[i]);
			userService.approveUser(approvedUserIds[i]);
		}

		return "trainer/dashboard";
	}


	/**
	 * @param model
	 * @param session
	 * @return trainee awaiting approvals page with list of trainees awaiting for trainer approval
	 * @author amit kolluri
	 */
	@RequestMapping(value = "/trainee/approve-awaiting-requests.htm", method = RequestMethod.GET)
	public String showTraineeAwaitingRequestsPage(Model model, HttpSession session) {


		List<User> awaitingTraineesList = userService.getAwaitingUsersList();
		List<Batch> batchList = batchService.listBatches();

		model.addAttribute("awaitingtraineeslist", awaitingTraineesList);
		model.addAttribute("batchList",batchList);
		return "trainer/trainee/trainee-awaiting-requests";
	}




}
