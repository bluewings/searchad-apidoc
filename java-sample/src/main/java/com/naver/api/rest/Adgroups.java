package com.naver.api.rest;

import com.mashape.unirest.http.HttpResponse;
import com.naver.api.model.Adgroup;
import com.naver.api.util.RestClient;

import java.util.Arrays;

public class Adgroups {
	/*

	그룹 목록 조회 GET /ncc/adgroups{?nccCampaignId}
	그룹 생성 POST /ncc/adgroups
	그룹 수정 PUT /ncc/adgroups/{adgroupId}{?fields}
	 */
	private static String apiPath = "/ncc/adgroups";

	public static Adgroup[] listByCampaignId(RestClient rest, long customerId, String campaignId) throws Exception {
		HttpResponse<String> response =
				rest.get(apiPath, customerId)
						.queryString("nccCampaignId", campaignId)
						.asString();
		return rest.asObject(response, Adgroup[].class);
	}

	public static Adgroup create(RestClient rest, long customerId, String campaignId, String name, String channelId) throws Exception {
		Adgroup adgroup = new Adgroup();
		adgroup.setNccCampaignId(campaignId);
		adgroup.setCustomerId(customerId);
		adgroup.setMobileChannelId(channelId);
		adgroup.setPcChannelId(channelId);
		adgroup.setName(name);
		adgroup.setBidAmt(70);

		HttpResponse<String> response =
				rest.post(apiPath, customerId)
						.body(adgroup)
						.asString();
		return rest.asObject(response, Adgroup.class);
	}

	public static Adgroup[] update(RestClient rest, long customerId, Adgroup adgroup, String fields) throws Exception {
		HttpResponse<String> response =
				rest.put(apiPath, customerId)
						.queryString("fields", fields)
						.body(Arrays.asList(adgroup))
						.asString();
		return rest.asObject(response, Adgroup[].class);
	}

	public static void delete(RestClient rest, long customerId, String adgroupId) throws Exception {
		HttpResponse<String> response =
				rest.delete(apiPath + "/" + adgroupId, customerId)
						.asString();
		rest.asObject(response, String.class);
	}

}
