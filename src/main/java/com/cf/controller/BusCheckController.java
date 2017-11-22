package com.cf.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.cf.entity.BusRuleEntity;
import com.cf.service.BusRuleService;
import com.cf.utils.*;
import it.sauronsoftware.jave.EncoderException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cf.entity.BusCheckEntity;
import com.cf.service.BusCheckService;
import org.springframework.web.multipart.MultipartFile;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;


/**
 * 
 * 
 * @author cf
 * @email 100@qq.com
 * @date 2017-11-17 15:20:26
 */
@RestController
@RequestMapping("buscheck")
public class BusCheckController {
	protected static Logger logger = LoggerFactory.getLogger(BusCheckController.class);

	@Autowired
	private BusCheckService busCheckService;

	@Autowired
	private BusRuleService busRuleService;

	@Autowired
	private  BaiduApiUtil baiduApiUtil;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("buscheck:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BusCheckEntity> busCheckList = busCheckService.queryList(query);
		int total = busCheckService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(busCheckList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("buscheck:info")
	public R info(@PathVariable("id") Integer id){
		BusCheckEntity busCheck = busCheckService.queryObject(id);
		
		return R.ok().put("busCheck", busCheck);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("buscheck:save")
	public R save(@RequestBody BusCheckEntity busCheck){
		busCheckService.save(busCheck);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("buscheck:update")
	public R update(@RequestBody BusCheckEntity busCheck){
		busCheckService.update(busCheck);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("buscheck:delete")
	public R delete(@RequestBody Integer[] ids){
		busCheckService.deleteBatch(ids);
		
		return R.ok();
	}


	/**
	 * 文件上传具体实现方法（单文件上传）
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file,@RequestParam("wavName") String file_name,Integer ruleId) {
		if (file.isEmpty()) throw new RRException("上传的文件为空，请重新上传...",202);
			try {
				String wavPath = Save2Disk(file, file_name);//保存到磁盘
				String content = baiduApiUtil.Speed2Text(wavPath);//语音识别内容
				BusRuleEntity rule = busRuleService.queryObject(ruleId);//通过ruleID获取规则内容
				String standard_text = rule.getStandardText();
				String degree = baiduApiUtil.compareSimilarDegree(content,standard_text);
				return degree;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
//		} else {
//			return "上传失败，因为文件是空的.";
//		}
	}


	private String Save2Disk(@RequestParam("file") MultipartFile file, @RequestParam("wavName") String file_name) throws IOException {
		String mp3Path = "D:/tmp/"+file_name;
		// 这里只是简单例子，文件直接输出到项目路径下。
		// 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
		// 还有关于文件格式限制、文件大小限制，详见：中配置。
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(mp3Path)));
		out.write(file.getBytes());
		out.flush();
		out.close();
		return mp3Path;
	}

	private static void Mp3ToWav(String mp3Path, String wavPath) {
		File source = new File(mp3Path);

		File target = new File(wavPath);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("pcm_alaw");
		audio.setBitRate(new Integer(16000));
		audio.setChannels(new Integer(1));
		audio.setSamplingRate(new Integer(16));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("wav");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		try {
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
	}


	public static void main(String[] args) {
//		// 初始化一个AipNlp
//		AipNlp client = new AipNlp(BaiduApiUtil.APP_ID, BaiduApiUtil.API_KEY, BaiduApiUtil.SECRET_KEY);
//		// 可选：设置网络连接参数
//		client.setConnectionTimeoutInMillis(2000);
//		client.setSocketTimeoutInMillis(60000);
//		// 选择CNN算法
//		HashMap<String, String> options = new HashMap<String, String>();
//		options.put("model", "BOW");
//		System.out.println(client.simnet("今天星期一","今天星期二",options));

//		String content = Speed2Text("D:/tmp/1511098628631.wav");//语音识别内容
		//String degree = BaiduApiUtil.compareSimilarDegree("今天2017年12月17日，销售人员张天爱，公司平安人寿杭州分公司，执业证号XX008，产品名称平安护身福终身寿险分红型,投保人为张凯，被保人为刘斌，投保人是被保险人的爸爸。","今天是2017年11月17日，地点：浙江省，销售人员张三，所属公司平安人寿大连分公司，执业证号AX009，产品名称平安护身福终身寿险（分红型）。投保人为张三，被保人为张三，投保人是被保险人的丈夫。");
//		System.out.println(degree);
	}
}
