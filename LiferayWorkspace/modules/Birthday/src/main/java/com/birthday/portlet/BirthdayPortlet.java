package com.birthday.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.service.component.annotations.Component;

import com.birthday.constants.BirthdayPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import obj.Worker;

/**
 * @author mrigr
 */
@Component(immediate = true, property = { "javax.portlet.version=3.0",
		"com.liferay.portlet.display-category=category.sample", 
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true", 
		"javax.portlet.display-name=Birthday",
		"javax.portlet.init-param.template-path=/", 
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BirthdayPortletKeys.BIRTHDAY, 
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class BirthdayPortlet extends MVCPortlet {
	private static final Logger logger = LogManager.getLogger("BirthdayPortlet");

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(renderRequest);
		String type = ParamUtil.getString(uploadRequest, "type");
		if (type.equalsIgnoreCase("filter")) {
			String date = ParamUtil.getString(uploadRequest, "date");
			renderRequest.setAttribute("workers", workersList(date));
		} else {
			renderRequest.setAttribute("workers", workersList());
		}
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		String type = ParamUtil.getString(uploadRequest, "type");
		if (type.equalsIgnoreCase("bday")) {
			System.out.println("Happy Birthday");
			logger.info("Happy Birthday");
		} else {
			System.out.println(type);
		}

		resourceResponse.setContentType("application/json");
		PrintWriter out = resourceResponse.getWriter();
		out.println("true");
		out.flush();
		super.serveResource(resourceRequest, resourceResponse);
	}

	private List<Worker> workersList(String date) {
		List<Worker> workers = new ArrayList<Worker>();

		workers.add(new Worker(1, "Barak", "01/07/1990"));
		workers.add(new Worker(2, "Linoy", "01/07/1980"));
		workers.add(new Worker(3, "Tom", "30/06/2000"));
		workers.add(new Worker(4, "Victor", "30/06/1990"));
		workers.add(new Worker(5, "Moshe", "01/07/1980"));
		workers.add(new Worker(6, "Liat", "30/06/2005"));

		if (date == null) {
			return workers;
		}

		try {
			new SimpleDateFormat("dd/MM").parse(date.trim());
		} catch (ParseException e) {
			return workers;
		}

		List<Worker> newList = new ArrayList<Worker>();
		for (Worker worker : workers) {
			if (worker.getBirthdate().substring(0, 5).equals(date)) {
				newList.add(worker);
			}
		}
		return newList;

	}

	private List<Worker> workersList() {
		return this.workersList(null);
	}
}