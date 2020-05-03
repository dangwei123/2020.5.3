请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。
class Solution {
    public int myAtoi(String str) {
        String s=str.trim();
        if("".equals(s)) return 0;
        if(s.charAt(0)=='+'||s.charAt(0)=='-'||(s.charAt(0)>='0'&&s.charAt(0)<='9')){
            boolean flag=false;
            int i=0;
            if(s.charAt(0)=='-'){
                flag=true;
                i++;
            }
            if(s.charAt(0)=='+') i++;
            int res=0;
            for(;i<s.length()&&s.charAt(i)>='0'&&s.charAt(i)<='9';i++){
                int num=s.charAt(i)-'0';
                if(!isValid(res,flag,num)){
                    if(flag) return Integer.MIN_VALUE;
                    return Integer.MAX_VALUE;
                }
                res=res*10+num;
            }
            return flag?-res:res;
        }
        return 0;
    }
    private boolean isValid(int n,boolean flag,int num){
        if(flag){
            if(n>Integer.MAX_VALUE/10||(n==Integer.MAX_VALUE/10&&num>=8)) return false;
        }else{
            if(n>Integer.MAX_VALUE/10||(n==Integer.MAX_VALUE/10&&num>=7)) return false;
        }
        return true;
    }
}

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(nums[i]>0) break;
            if(i>0&&nums[i]==nums[i-1]) continue;
            int left=i+1;
            int right=nums.length-1;
            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if(sum==0){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    while(left<right&&nums[left]==nums[left-1]) left++;
                    right--;
                    while(left<right&&right<nums.length-1&&nums[right]==nums[right+1]) right--;
                }else if(sum<0){
                    left++;
                    while(left<right&&nums[left]==nums[left-1]) left++;
                }else{
                     right--;
                    while(left<right&&right<nums.length-1&&nums[right]==nums[right+1]) right--;
                }
            }
        }
        return res;
    }
}

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                char t=stack.pop();
                if((t=='('&&c==')')||(t=='['&&c==']')||(t=='{'&&c=='}')){
                    
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
class Solution {
    public int removeDuplicates(int[] nums) {
        int j=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[j]){
                nums[++j]=nums[i];
            }
        }
        return ++j;
    }
}

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
class Solution {
    public String multiply(String num1, String num2) {
        if("0".equals(num1)||"0".equals(num2)) return "0";
        int m=num1.length();
        int n=num2.length();
        int[] arr=new int[m+n];
        for(int i=m-1;i>=0;i--){
            int a=num1.charAt(i)-'0';
            for(int j=n-1;j>=0;j--){
                int b=num2.charAt(j)-'0';
                int num=arr[i+j+1]+a*b;
                arr[i+j+1]=num%10;
                arr[i+j]+=num/10;
            }
        }
        StringBuilder sb=new StringBuilder();
        if(arr[0]==0){
            for(int i=1;i<arr.length;i++){
                sb.append(arr[i]);
            }
        }else{
            for(int i=0;i<arr.length;i++){
                sb.append(arr[i]);
            }
        }
        return new String(sb);
    }
}

给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res=new ArrayList<>();
        int up=0;
        int down=matrix.length-1;
        if(down==-1) return res;
        int left=0;
        int right=matrix[0].length-1;
        while(true){
            for(int i=left;i<=right;i++) res.add(matrix[up][i]);
            if(++up>down) break;
            for(int i=up;i<=down;i++) res.add(matrix[i][right]);
            if(--right<left) break;
            for(int i=right;i>=left;i--) res.add(matrix[down][i]);
            if(--down<up) break;
            for(int i=down;i>=up;i--) res.add(matrix[i][left]);
            if(++left>right) break;
        }
        return res;
    }
}

给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix=new int[n][n];
        int left=0;
        int up=0;
        int down=n-1;
        int right=n-1;
        int j=1;
        while(true){
            for(int i=left;i<=right;i++) matrix[up][i]=j++;
            if(++up>down) break;
            for(int i=up;i<=down;i++) matrix[i][right]=j++;
            if(--right<left) break;
            for(int i=right;i>=left;i--) matrix[down][i]=j++;
            if(--down<up) break;
            for(int i=down;i>=up;i--) matrix[i][left]=j++;
            if(++left>right) break;
        }
        return matrix;
    }
}

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int min=nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            int left=i+1;
            int right=nums.length-1;
            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if(Math.abs(sum-target)<Math.abs(min-target)){
                    min=sum;
                }
                if(sum>target){
                    right--;
                }else if(sum<target){
                    left++;
                }else{
                    return target;
                }
            }
        }
        return min;
    }
}