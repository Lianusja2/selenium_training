/**
 * Created by inna.pshenychna on 6/15/2017.
 */
public enum TabNamesAC {
    Appearance("Appearance","Template","?app=appearance&doc=template"),
    Catalog("Catalog","Catalog","?app=catalog&doc=catalog"),
    Countries("Countries","Countries","?app=countries&doc=countries"),
    Currencies("Currencies","Currencies","?app=currencies&doc=currencies"),
    Customers("Customers","Customers","?app=customers&doc=customers"),
    Geo_Zones("Geo Zones","Geo Zones","?app=geo_zones&doc=geo_zones"),
    Languages("Languages","Languages","?app=languages&doc=languages"),
    Modules("Modules","Modules","?app=modules&doc=jobs"),
    Orders("Orders","Orders","?app=orders&doc=orders"),
    Pages("Pages","Pages","?app=pages&doc=pages"),
    Reports("Reports","Monthly Sales","?app=reports&doc=monthly_sales"),
    Settings("Settings","Settings","?app=settings&doc=store_info"),
    Slides("Slides","Slides","?app=slides&doc=slides"),
    Tax("Tax","Tax","?app=tax&doc=tax_rates"),
    Translations("Translations","Translations","?app=translations&doc=search"),
    Users("Users","Users","?app=users&doc=users"),
    vQmods("vQmods","vQmods","?app=vqmods&doc=vqmods");


    private String displayName;
    private String headerText;
    private String linkPart;


    TabNamesAC( String displayName,String headerText, String linkPart) {


        this.displayName = displayName;
        this.headerText = headerText;
        this.linkPart = linkPart;

    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHeaderText() {
        return headerText;
    }

    public String getLinkPart() {
        return linkPart;
    }

}
