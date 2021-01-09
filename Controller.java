class Controller extends Model{	
	 int getX(Model temp){
		return temp.x;
	}
	 int getY(Model temp){
		return temp.y;
	}
	 int getWidth(Model temp){
		return temp.w;
	}
	 int getHeight(Model temp){
		return temp.h;
	}	
	 String getVar(Model temp){
		return temp.var;
	}
	 String getText(Model temp){
		return temp.text;
	}
	 int getNum(Model temp){
		return temp.num;
	}
	 int getSerial(Model temp){
		return temp.Serial;
	}
	 void setX(Model temp, int x){
		temp.x = x;
	}
	 void setY(Model temp, int y){
		temp.y = y;
	}
	 void setWidth(Model temp, int w){
		temp.w = w;
	}
	 void setHeight(Model temp, int h){
		temp.h = h;
	}
	 void setVar(Model temp, String var){
		temp.var = var;
	 }
	 void setText(Model temp, String text){
		temp.text = text;
	}
	 void setNum(Model temp, int num){
		temp.num = num;
	}
	 void setSerial(Model temp, int Serial){
		temp.Serial = Serial;
	}
}