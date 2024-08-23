package jpastore.jpaticket.dto;

public class GameDto {
    private Long id;
    private String title;
    private String gameDate;
    private String gameTime;
    private int price;
    private String redTeam;
    private String blueTeam;
    private String place;

    // 기본 생성자
    public GameDto() {}

    // 필요한 경우, 모든 필드를 초기화하는 생성자
    public GameDto(Long id, String title, String gameDate, String gameTime, int price, String redTeam, String blueTeam, String place) {
        this.id = id;
        this.title = title;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.price = price;
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
        this.place = place;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGameDate() { return gameDate; }
    public void setGameDate(String gameDate) { this.gameDate = gameDate; }

    public String getGameTime() { return gameTime; }
    public void setGameTime(String gameTime) { this.gameTime = gameTime; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getRedTeam() { return redTeam; }
    public void setRedTeam(String redTeam) { this.redTeam = redTeam; }

    public String getBlueTeam() { return blueTeam; }
    public void setBlueTeam(String blueTeam) { this.blueTeam = blueTeam; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }
}
