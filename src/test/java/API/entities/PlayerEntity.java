package API.entities;


public class PlayerEntity {

    private Long id;
    private Long playerKey;
    private String name;

    public PlayerEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(Long playerKey) {
        this.playerKey = playerKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id=" + id +
                ", playerKey=" + playerKey +
                ", name='" + name + '\'' +
                '}';
    }
}
