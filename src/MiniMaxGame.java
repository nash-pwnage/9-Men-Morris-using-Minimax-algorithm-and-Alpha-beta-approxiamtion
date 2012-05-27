import java.io.*;
import java.util.*;

public class MiniMaxGame {
	
		static String bestmove=new String();
		static int count_new=0;
		static int count_est=0;
		static int count_min=0;
		static int[] neighbors(int j)
		{	int[] a=new int[4];
			switch(j){
			case 0: a[0]=1;a[1]=3;a[2]=8;a[3]=999;break;
			case 1: a[0]=0;a[1]=4;a[2]=2;a[3]=999;break;
			case 2:a[0]=1;a[1]=5;a[2]=13;a[3]=999;break;
			case 3:a[0]=0;a[1]=9;a[2]=4;a[3]=6;break;
			case 4:a[0]=1;a[1]=3;a[2]=5;a[3]=999;break;
			case 5:a[0]=4;a[1]=7;a[2]=12;a[3]=2;break;
			case 6:a[0]=3;a[1]=7;a[2]=10;a[3]=999;break;
			case 7:a[0]=6;a[1]=11;a[2]=5;a[3]=999;break;
			case 8:a[0]=0;a[1]=9;a[2]=20;a[3]=999;break;
			case 9:a[0]=3;a[1]=8;a[2]=17;a[3]=10;break;
			case 10:a[0]=6;a[1]=14;a[2]=9;a[3]=999;break;
			case 11:a[0]=7;a[1]=16;a[2]=12;a[3]=999;break;
			case 12:a[0]=5;a[1]=11;a[2]=19;a[3]=13;break;
			case 13:a[0]=2;a[1]=12;a[2]=22;a[3]=999;break;
			case 14:a[0]=10;a[1]=15;a[2]=17;a[3]=999;break;
			case 15:a[0]=18;a[1]=14;a[2]=16;a[3]=999;break;
			case 16:a[0]=11;a[1]=15;a[2]=19;a[3]=999;break;
			case 17:a[0]=9;a[1]=20;a[2]=18;a[3]=14;break;
			case 18:a[0]=15;a[1]=17;a[2]=21;a[3]=19;break;
			case 19:a[0]=12;a[1]=16;a[2]=18;a[3]=22;break;
			case 20:a[0]=8;a[1]=17;a[2]=21;a[3]=999;break;
			case 21:a[0]=20;a[1]=18;a[2]=22;a[3]=999;break;
			case 22:a[0]=13;a[1]=19;a[2]=21;a[3]=999;break;
			}
			if(a[3]==999)
			{
				int[] b = new int[3];
				b[0]=a[0];
				b[1]=a[1];
				b[2]=a[2];
				return b;
			}
			else {
				return a;
			}
		}
		static ArrayList<String> GenerateMovesMidgameEndgame(char[] a,char player)
		{
			int count=0,i=0;
			for(i=0;i<22;i++)
				if(a[i]==player) count++;
			if(count<=3) return (GenerateHopping(a));
			else return (GenerateMove(a));
		}
		static ArrayList<String> GenerateHopping(char[] board)
		{
			ArrayList<String> L=new ArrayList<String>();
			for(int alpha=0;alpha<22;alpha++)
			{
				if(board[alpha]=='W')
				{
					for(int beta=0;beta<22;beta++)
					{
						if(board[beta]=='x')
						{
							char[] b=new char[22];
							for(int j=0;j<22;j++)
								{
								b[j]=board[j];
								}
							b[alpha]='x';
							b[beta]='W';
							if (closemill(beta,b)) GenerateRemove(b,L);
							else L.add(b.toString());
						}
					}
					
				}
				
			}
			return L;
		}
		static ArrayList<String> GenerateMove(char[] board)
		{
			ArrayList<String> L=new ArrayList<String>();
			for(int i=0;i<22;i++)
			{
				if(board[i]=='W')
				{try{
					int[] n=neighbors(i);
					for(int j=0;j<n.length;j++)
					{
						if(board[j]=='x')
						{
							char[] b=new char[22];
							for(int j1=0;j1<22;j1++)
								{
								b[j1]=board[j1];
								}
							b[i]='x';
							b[j]='W';
							if(closemill(j,b)==true) {L=GenerateRemove(b,L);}
							else {String a=new String(b);
								L.add(a);}
						}
						
					}
				}catch(Exception e){return L;}
				}
			}
			return L;
		}
		/*static ArrayList<String> GenerateAdd(char[] board,char player)
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
		}*/
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
				if(num==L.size()) 
				{
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
			
			if(depth==0) {count_new++;
				count_min+=766;return static_estimation(board);}
			else 
				
			{	count_new++;
				count_min+=22;
				int v = 100000; 
				ArrayList<String> L= new ArrayList<String>();
				L=GenerateMove(board);
				char[] n=new char[22];
				for(int i=0;i<L.size();i++)
				{if(L.get(i)!=null)
					bestmove=L.get(i);
					n=L.get(i).toCharArray();
					v=Math.min(v+1,MaxMin(n,depth-1));}
				
				return v;
			}
		}
		static int MaxMin(char[] board,int depth)
		{
			if(depth==0) {count_new++;count_min+=99;return static_estimation(board);}
			else 
			{	count_new++;
				count_min+=74;
				char[] n=new char[22];
				int v = -100000; 
				ArrayList<String> L= new ArrayList<String>();
				L=GenerateMove(board);
				
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
			L=GenerateMove(b);
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


