package az.inci.zreport.model;

public enum Month
{
    YANVAR(1, "Yanvar"),
    FEVRAL(2, "Fevral"),
    MART(3, "Mart"),
    APREL(4, "Aprel"),
    MAY(5, "May"),
    IYUN(6, "İyun"),
    IYUL(7, "İyul"),
    AVQUST(8, "Avqust"),
    SENTYABR(9, "Sentyabr"),
    OKTYABR(10, "Oktyabr"),
    NOYABR(11, "Noyabr"),
    DEKABR(12, "Dekabr"),
    CURRENT(0, "Cari ay");


    Month(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public static Month getById(int id)
    {
        for(Month month : values())
        {
            if(month.id == id)
                return month;
        }

        return CURRENT;
    }
}
