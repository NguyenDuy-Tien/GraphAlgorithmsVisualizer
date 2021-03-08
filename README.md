# JEFF BEZOS

## PERSONAL FINANCIAL MANAGEMENT APPLICATION

This is the work of 3 uni students:

<i>Tran Lam 20194787</i>

<i>Tran Quang Hai 20194755</i>

<i>Nguyen Duy Tien 20194857</i>


## Conventions On How To Write Everything:

### Git commit message writing rules:

1. Separate subject from body with a blank line
2. Limit the subject line to 50 characters
3. Capitalize the subject line
4. Do not end the subject line with a period
5. Use the imperative mood in the subject line
6. Wrap the body at 72 characters
7. Use the body to explain what and why vs. how

Or simply, write a commit message by filling the follow sentence:

When applied, this commit will <b>your commit message here</b>

> (more details on: https://chris.beams.io/posts/git-commit/)

### Git workflow:
* <b> DON'T TOUCH THE MASTER BRANCH </b> without issuing a group meeting first.	
* All works in progress (WIP) or commits that have not been tested thoroughly 
	will sprout from <i>unstable</i> branch which directly diverged from <i>master</i> branch.
This workflow is illustrated as follow: 

![Git Workflow](GitWorkflow.png)

### Java coding convention:
1. Names should be meaningful.
2. Names should be as short as possible (as long as they don't conflict with <i>Rule #1</i>)
3. All member variables are prefixed with "\_"
4. <b>PascalCase</b> for class names
5. <b>camelCase</b> for function names (except <i>main</i>)
6. <b>Snake\_Case</b> for variable names.
7. In a class, visible (public) functions and variables will be put at the beginning of the class. All hidden (private) ones will be put on tail.
8. Comments on each functions. What does it do? What its arguments are? What does it return.
9. Comments on your code whenever possible. Explain why you do it.

> 99. <i>Put class implementation in a separate file (if implementation exceeds 100 lines) (if Java supports this)  </i>
> 100. <i>If not, put class implementation below class prototype (if Java supports this) </i>

Here's an example:
	
	// ASmallExample.java
	
	public int i=0;		// WRONG! What does 'i' do?
	public int Mistakes_Count=1; // Right. This variable

	// Print out anything you give it 
	public void saySomething(String Speech)
	{
		System.out.println(Speech);	
	}

	public class ASmallExample // PascalCase for class names
	{
		public static void main(String[] args)
		{
			System.exit(0);
		}

		// Visible parts of the class at the beginning

		public int _First_Member_Variable; // "_" prefix 
		public String _Second_Variable;	   // and Snake_Case 

		/*
			<i>Rule 6</i>
			Add a number with the number of this variable, then turn the result into String	
			@param i: The number you want to transform
			@return: The number in string
		*/
		public String intToString(int i) // camelCase for functions
		{
			int result = i + this._First_Member_Variable;
			
			// I could have write a for loop here for <i>Rule 7</i> illustration
			// But that takes too much time
			String Str = String.valueOf(result);
			return Str;
		}

	
		// Hidden parts at the end
		private String _Dr_Jekyll;
		private void mrHyde();
	}
