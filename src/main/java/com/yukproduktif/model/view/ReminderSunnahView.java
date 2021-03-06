package com.yukproduktif.model.view;

import java.util.Arrays;
import java.util.List;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.Template;
import com.yukproduktif.model.ReminderSunnah;

/**
 * View Seting Reminder Sunnah
 * @author Muhammad Imam Fauzan
 * View untuk menampilkan setting reminder ibadah sunnah (tahajud, dhuha, shaum senin-kamis)
 * View ditampilkan dalam bentuk carousel (3 kolom)
 * Setiap view memiliki 1 buah button untuk mengaktifkan / me-nonaktifkan reminder
 * Field jam dibawah belum diganti, seharusnya yang ditampilkan bukan jam, tapi text / suatu keterangan.
 */
public class ReminderSunnahView {
	
	private final static String TITLE = "Setting Reminder Sunnah";
	private final static String IMAGE_URL_DHUHA = "https://dalamruangkeheningan.files.wordpress.com/2017/05/designf.jpg";
	private final static String IMAGE_URL_TAHAJUD = "https://dalamruangkeheningan.files.wordpress.com/2017/05/designh.jpg";
	
	private List<CarouselColumn> columns;
	private Template carousel;
	private TemplateMessage viewMessage;
	
	public ReminderSunnahView(){
		//create view carousel message				
		columns = Arrays.asList(
			new CarouselColumn(IMAGE_URL_DHUHA,"Reminder Dhuha","08:30", Arrays.asList(new MessageAction("Aktifkan", "reminder dhuha on"))),
			new CarouselColumn(IMAGE_URL_TAHAJUD,"Reminder Tahajud","02:30", Arrays.asList(new MessageAction("Aktifkan", "reminder tahajud on")))
		);
				
		carousel = new CarouselTemplate(columns);
		viewMessage = new TemplateMessage(TITLE, carousel);
				
	}
	
	public ReminderSunnahView(ReminderSunnah reminder){
		//create view carousel message				
		columns = Arrays.asList(
			new CarouselColumn(IMAGE_URL_DHUHA, "Reminder Dhuha", "08:30", 
					Arrays.asList(new MessageAction(getActiveText(reminder.isDhunaActive()), getRequestText("dhuha", reminder.isDhunaActive())))),
			
			new CarouselColumn(IMAGE_URL_TAHAJUD, "Reminder Tahajud", "02:30", 
					Arrays.asList(new MessageAction(getActiveText(reminder.isTahajudActive()), getRequestText("tahajud", reminder.isTahajudActive()))))
		);
				
		carousel = new CarouselTemplate(columns);
		viewMessage = new TemplateMessage(TITLE, carousel);				
	}
		
	public TemplateMessage getViewMessage(){
		return viewMessage;
	}
	
	private String getActiveText(boolean active){
		return active ? "Non-Aktifkan" : "Aktifkan";
	}
	
	private String getRequestText(String prayerName, boolean active){
		return "reminder " + prayerName + (active ? " off" : " on");
	}

}
