package bo;

public class PhanTrangBO {
	final int ROW_COUNT_ADMIN = 2;
	final int ROW_COUNT_PUBLIC = 5;
	public int getRowCountAdmin(){
		return ROW_COUNT_ADMIN;
	}
	public int getRowCountPublic(){
		return ROW_COUNT_PUBLIC;
	}
	public int getOffset(int current_page, int row_count){
		return (current_page - 1) * row_count;
	}
	public int getSoTrang(int total, int row_count){
		return (int) Math.ceil((float) total / row_count);
	}
}
