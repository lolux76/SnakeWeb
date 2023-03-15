package beans;

import java.util.UUID;

import org.json.JSONStringer;

public class Game {
	private String uuid;
	private String player_one_uuid;
	private String player_two_uuid;
	private int player_one_score;
	private int player_two_score;
	
	public Game(String player_one_uuid, String player_two_uuid) {
		this.uuid = UUID.randomUUID().toString();
		this.player_one_uuid = player_one_uuid;
		this.player_two_uuid = player_two_uuid;
		this.player_one_score = 0;
		this.player_two_score = 0;
	}
	
	public Game(String uuid, String player_one_uuid, String player_two_uuid, int player_one_score, int player_two_score) {
		this.uuid = uuid;
		this.player_one_uuid = player_one_uuid;
		this.player_two_uuid = player_two_uuid;
		this.player_one_score = player_one_score;
		this.player_two_score = player_two_score;
	}
	
	public String toJson() {
		JSONStringer json = new JSONStringer();
		json.object(); //nouvel objet json
		json.key("uuid").value(uuid);
		json.key("player_one_uuid").value(player_one_uuid);
		json.key("player_two_uuid").value(player_two_uuid);
		json.key("player_one_score").value(player_one_score);
		json.key("player_two_score").value(player_two_score);
		json.endObject(); //fin de l'objet json
		return json.toString();
	}
	
	public String getUuid() {
		return uuid;
	}

	public String getPlayer_one_uuid() {
		return player_one_uuid;
	}

	public String getPlayer_two_uuid() {
		return player_two_uuid;
	}

	public int getPlayer_one_score() {
		return player_one_score;
	}

	public int getPlayer_two_score() {
		return player_two_score;
	}
}
