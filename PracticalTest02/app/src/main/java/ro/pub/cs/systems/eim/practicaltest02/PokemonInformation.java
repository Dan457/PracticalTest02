package ro.pub.cs.systems.eim.practicaltest02;

public class PokemonInformation {

    private String abilities;
    private String type;
    private String imageUrl;

    public PokemonInformation() {
        this.abilities = null;
        this.type = null;
        this.imageUrl = null;
    }

    public PokemonInformation(String abilities, String type, String imageUrl) {
        this.abilities = abilities;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "PokemonInformation{" +
                "abilities='" + abilities + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
