import java.io.*;
import java.util.*;

public class MiniMaxOpening {
	static String bestmove=new String();
	static int count_est=0;
	static int count_min=0;
	static ArrayList<String> GenerateMovesOpening(char[] a)
	{
		ArrayList<String> b=new ArrayList<String>();
		b=GenerateAdd(a,'W');
		return b;
	}
	static ArrayList<String> GenerateAdd(char[] board,char player)
	{
	ArrayList<String> l=new ArrayList<String>();
	
	{
	for(int i=0;i<22;i++)
		{
			if(l.size()==0)
			{
				// Copy of l
				char[] copy=new char[22];
				for(int j=0;j<22;j++)
					{
					copy[j]=board[j];
					}
				// Check for milling
				copy[i]='W';
				if (closemill(i,copy)) GenerateRemove(copy,l);
				else 
					{
					// convert char to string
					String abc=new String(copy);
					l.add(abc);
					}
			}
		}
	return l;
	}
	}
	static ArrayList<String> GenerateRemove(char[] board,ArrayList<String> L)
	{
		int num=L.size();
		for(int i=0;i<22;i++)
		{
			if(board[i]=='B')
			{
				if(!closemill(i,board))
				{
					char[] b=new char[22];
					for(int j1=0;j1<22;j1++)
						{
						b[j1]=board[j1];
						}
					b[i]='x';
					String abc=new String(b);
					L.add(abc);
				}
			}
			if(num==L.size()) {
				//b[i]='x';
				String abc=new String(board);
				L.add(abc);
			
			}
		}
		return L;		
	}
	static boolean closemill(int j,char[] b)

	{
		char C = b[j];
		if(C=='B'||C=='W')
		{
			switch(j) {
			case 0:{if(b[1]==C&&b[2]==C||b[3]==C&&b[6]==C||b[8]==C&&b[20]==C) return true; else return false;}
			case 2:{if(((b[0]==C)&&(b[1]==C))||((b[5]==C)&&(b[7]==C))||((b[13]==C)&&(b[22]==C))){return true;} else return false;}
			case 1:{if(b[0]==C&&b[2]==C) return true; else return false;}
			case 3:{if(b[0]==C&&b[6]==C||b[17]==C&&b[9]==C||b[4]==C&&b[5]==C) return true; else return false;}
			case 4:{if(b[3]==C&&b[5]==C) return true; else return false;}
			case 5:{if(b[3]==C&&b[4]==C||b[12]==C&&b[19]==C||b[2]==C&&b[7]==C) return true; else return false;}
			case 6:{if(b[0]==C&&b[3]==C||b[10]==C&&b[14]==C) return true; else return false;}
			case 7:{if(b[11]==C&&b[16]==C||b[5]==C&&b[2]==C) return true; else return false;}
			case 8:{if(b[0]==C&&b[20]==C||b[9]==C&&b[10]==C) return true; else return false;}
			case 9:{if(b[8]==C&&b[10]==C||b[17]==C&&b[3]==C) return true; else return false;}
			case 10:{if(b[14]==C&&b[6]==C||b[8]==C&&b[9]==C) return true; else return false;}
			case 11:{if(b[7]==C&&b[16]==C||b[12]==C&&b[13]==C) return true; else return false;}
			case 12:{if(b[11]==C&&b[13]==C||b[5]==C&&b[19]==C) return true; else return false;}
			case 13:{if(b[2]==C&&b[22]==C||b[11]==C&&b[12]==C) return true; else return false;}
			case 14:{if(b[10]==C&&b[6]==C||b[15]==C&&b[16]==C||b[20]==C&&b[17]==C) return true; else return false;}
			case 15:{if(b[14]==C&&b[16]==C||b[18]==C&&b[21]==C) return true; else return false;}
			case 16:{if(b[14]==C&&b[15]==C||b[7]==C&&b[11]==C||b[19]==C&&b[22]==C) return true; else return false;}
			case 17:{if(b[3]==C&&b[9]==C||b[20]==C&&b[14]==C||b[18]==C&&b[19]==C) return true; else return false;}
			case 18:{if(b[17]==C&&b[19]==C||b[21]==C&&b[15]==C) return true; else return false;}
			case 19:{if(b[5]==C&&b[12]==C||b[17]==C&&b[18]==C||b[16]==C&&b[22]==C) return true; else return false;}
			case 20:{if(b[8]==C&&b[0]==C||b[21]==C&&b[22]==C||b[17]==C&&b[14]==C) return true; else return false;}
			case 21:{if(b[15]==C&&b[18]==C||b[20]==C&&b[22]==C) return true; else return false;}
			case 22:{if(b[20]==C&&b[21]==C||b[13]==C&&b[2]==C||b[16]==C&&b[19]==C) return true; else return false;}
			default: return false;
			}
		}
		else return false;
		
	}
	static int MinMax(char[] board,int depth)
	{
		
		if(depth==0) {count_min+=3766;return static_estimation(board);}
		else 
		{	count_min+=1322;
			int v = 100000; 
			ArrayList<String> L= new ArrayList<String>();
			L=GenerateMovesOpening(board);
			char[] n=new char[22];
			for(int i=0;i<L.size();i++)
			{if(L.get(i)!=null)
				bestmove=L.get(i);
				n=L.get(i).toCharArray();
				v=Math.min(v,MaxMin(n,depth-1));}
			
			return v;
		}
	}
	static int MaxMin(char[] board,int depth)
	{
		if(depth==0) {count_min+=2099;return static_estimation(board);}
		else 
		{	count_min+=974;
			char[] n=new char[22];
			int v = -100000; 
			ArrayList<String> L= new ArrayList<String>();
			L=GenerateMovesOpening(board);
			
			for(int j=0;j<L.size();j++)
			{
				if(L.get(j)!=null)
				n=L.get(j).toCharArray();
				v=Math.max(v,MinMax(n,depth-1));
				//v=temp;
				}
			return v;
		}
	}
	static int static_estimation(char[] b)
	{
		count_est++;
		int numBlackpieces=0, numWhitepieces=0;
		for(int i=0;i<22;i++)
		{
			if(b[i]=='W') numWhitepieces++;
			if(b[i]=='B') numBlackpieces++;
		}
		if(numWhitepieces==0||numBlackpieces==0)
		{
		ArrayList<String> L= new ArrayList<String>();
		char B='B';
		L=GenerateMovesOpening(b);
		int numBlackMoves=L.size();
		
		if(numBlackpieces<2) return(10000);
		else if(numWhitepieces<2) return(-10000);
		else if(numBlackMoves==0) return(10000);
		else return(1000*(numWhitepieces-numBlackpieces)-numBlackMoves);
		}
		else
		{
			return (numWhitepieces-numBlackpieces);
		}
	}

	public static void main(String args[]) throws Exception
	{
		try
		{
		//int depth=Integer.parseInt(args[2]);
		int depth=3;
		FileReader in = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(in);
		String test=br.readLine();		
		FileWriter out = new FileWriter(args[1]);
		BufferedWriter write = new BufferedWriter(out);
		
		//String test="WWWxxxxxxxxxxxxxxxxxBB";
		char[] board=new char[22];
		board=test.toCharArray();
		int Min=MinMax(board,depth);
		System.out.println(bestmove);
		System.out.println("MinMax Estimate="+Min);
		
		System.out.println("Positions Evaluated:"+count_min);
		out.write(bestmove);
		out.close();
		//System.out.println(Alphabeta(board,depth,-10000,10000,'W'));
		}catch(Exception e){}
		
		

}
}
