package tp1.adom;

public class Ville {

	int pos;
	int x;
	int y;

	public Ville(int pos, int x, int y) {
		this.pos = pos;
		this.x = x;
		this.y = y;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double distance(Ville v2) {
		return Math.round(Math.sqrt(Math.pow((this.x - v2.getX()), 2) + Math.pow((this.y - v2.getY()), 2)));
	}

	@Override
	public String toString() {
		//return "Ville [pos=" + pos + ", x=" + x + ", y=" + y + "]";
		return "[" + pos + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (pos != other.pos)
			return false;
		return true;
	}
	
	

}
