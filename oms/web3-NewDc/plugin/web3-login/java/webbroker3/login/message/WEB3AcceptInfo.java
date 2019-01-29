head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.05.27.39;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3c84d8ad6447b3c;
filename	WEB3AcceptInfo.java;

1.1
date	2011.03.15.05.27.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptInfo.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name           : �ڋq����xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3AcceptInfo.java)
Author Name         : Daiwa Institute of Research Business Innovation
Revesion History    : 2004/04/28 �e�n(SRA)
Revesion History    : 2006/08/24 �h�C (���u)�d�l�ύX�E���f��030
Revesion History    : 2007/06/12 ������ (���u)�d�l�ύX�E���f��034
Revesion History    : 2007/08/30 ���n�m (���u)�d�l�ύX�E���f��044
Revesion History    : 2007/10/31 �đo�g (���u) �d�l�ύX�E���f��048
Revesion History    : 2008/01/28 ���V�� (���u) �d�l�ύX�E���f��050
Revesion History    : 2008/02/15 ���g (���u) �d�l�ύX�E���f��054
Revesion History    : 2009/03/12 �Ԑi (���u) �d�l�ύX�E���f��057
Revesion History    : 2010/11/10 �����C (���u) �d�l�ύX�E���f��058
 */

package webbroker3.login.message;


import java.util.Date;

import webbroker3.common.define.WEB3EraBornDef;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�ڋq���)<BR>
 * �ڋq����xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X<BR>
 * xTrade���X�|���X�Ɋ܂߂���l��xTrade.Message���p������<BR>
 * �쐬����WOLF2���Z�b�V�����ێ��p�̌ڋq���i�[�N���X�B<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptInfo extends Message 
{
   /**
    * ����|�������N
    */
   public static final int AD_MEIJI = 1867;
   
   /**
    * ����|�吳���N
    */
   public static final int AD_TAISYO = 1911;
   
   /**
    * ����|���a���N
    */
   public static final int AD_SYOWA = 1925;
   
   /**
    * ����|�������N
    */
   public static final int AD_HEISEI = 1988;
   
   /**
    * (�ڋq�R�[�h)<BR>
    * ��ʂ�����͂��ꂽ�U���̌ڋq�R�[�h�B<BR>
    */
   public String acceptCode;
   
   /**
    * �iCD�t�ڋq�R�[�h�j<BR>
    * CD�t�V���̌ڋq�R�[�h�B<BR>
    */
   public String acceptCodeCD;
   
   /**
    * (xTrade���[�U��)
    */
   public String xTradeUsername;
   
   /**
    * (����ID)<BR>
    * xTrade���ڋq�}�X�^��PK�B<BR>
    */
   public long accountID;
   
   /**
    * (���O�i�����j)<BR>
    * �ڋq�}�X�^�D���O�i�c���j<BR>
    */
   public String nameKanji;
   
   /**
    * (���O�i�J�i�j)<BR>
    * �ڋq�}�X�^�D���O�i�c��1�j<BR>
    */
   public String nameKana;
   
   /**
    * (����)
    */
   public String sex;
   
   /**
    * (�ŏI���O�C������)
    */
   public Date lastLoginTime;
   
   /**
    * (�M�p����o�^�t���O)<BR>
    * �o�^�ς�=true�^���o�^=false<BR>
    */
   public boolean marginRegistFlag;
   
   /**
    * (�敨OP����o�^)<BR>
    * 0�F�@@�o�^�Ȃ�<BR>
    * 1�F�@@�o�^�ς݁iOP��������j <BR>
    * 2�F�@@�o�^�ς݁i�敨����j <BR>
    * 3�F�@@�o�^�ς݁i�敨�^OP����j<BR> 
    */
   public String futOpTradeRegist;
   
   /**
    * (�ݓ��o�^�t���O)<BR>
    * �o�^�ς�=true�^���o�^=false<BR>
    */
   public boolean ruitoRegistFlag;
   
   /**
    * (���A�������擾�t���O)<BR>
    * ���A���������擾�ł���ꍇ=true�^�f�B���C�����̏ꍇ=false<BR>
    */
   public boolean quotoFlag;
   
   /**
    * (��~�t���O)<BR>
    * �����~�ڋq�̏ꍇ=true�^�����~�ڋq�ȊO=false<BR>
    */
   public boolean stopFlag;
   
   /**
    * (�x�q�t���O)<BR>
    * Y�q�̏ꍇ=true�^Y�q�ȊO=false<BR>
    */
   public boolean yellowCustomerFlag;
   
   /**
    * (�ב֕ۏ؋������J�݋敪)<BR>
    * 0�F�����Ȃ�  1�F�����J��  2�F��������<BR>
    */
   public String fxAccOpenDiv;
   
   /**
    * (�O�������A�g�����J�݋敪)<BR>
    * 0�F�����Ȃ�  1�F�����J��  2�F��������<BR>
    */
   public String fstkCooperateAccOpenDiv;
   
   /**
    * (�O���،������J�݋敪)<BR>
    * 0:�����Ȃ��@@1:�����J��<BR>
    */
   public String feAccOpenDiv;
   
   /**
    * (�a��،��]�����敪)<BR>
    * 0:�����{�@@1:���{<BR>
    */
   public String assetEvaluationDiv;

   /**
    * (�X�g�b�N�I�v�V���������J�݋敪)<BR>
    * 0:�����Ȃ��@@1:�����J��<BR>
    */
   public String stockOpAccOpenDiv;
   
   /**
    * (���o�C����p�����J�݋敪)<BR>
    * 0:�����Ȃ��@@1:�����J��<BR>
    */
   public String mobileAccOpenDiv;

   /**
    * (�S�ۃ��[�������J�݋敪)<BR>
    * 0:�����Ȃ��@@1:�����J��<BR>
    */
   public String securedLoanAccOpenDiv;

   /**
    * (���ʌ�t�Ǘ����)<BR>
    * ���ʌ�t�Ǘ����<BR>
    */
   public WEB3DocumentDeliverInfoUnit[] documentDeliverList;

   /**
    * SONAR���ҏ��
    */
   public WEB3SonarOperatorInfo sonarOperatorInfo;

   /**
    * (PTS�����J�݋敪)<BR>
    * 0:�����Ȃ��@@1:�����J��<BR>
    */
   public String ptsAccOpenDiv;

   /**
    * (�����J�݋敪�ꗗ)<BR>
    * �����J�݋敪�ꗗ<BR>
    */
   public WEB3AccOpenDivUnit[] accOpenDivList;

   /**
    * (�d�q��t�\���`�F�b�N�t���O)<BR>
    * 0�F���ς݁@@1�F�ς�<BR>
    */
   public String edCheckFlag;

   /**
    * @@roseuid 4021A07F0167
    */
   public WEB3AcceptInfo() 
   {
    
   }
   
   /**
    * (calc���N�����i����j)<BR>
    * �����F���N�����N���A���N�����i�a��j���琶�N�����i����j���v�Z����B<BR>
    * @@param l_eraBorn - 
    * @@param l_bornDate - 
    * @@return java.lang.String
    * @@roseuid 4016456E0370
    */
   public static String calcADBornDate(String l_eraBorn, String l_bornDate) 
   {
       if ( (l_eraBorn == null) || (l_bornDate == null) )
       {
           return null;
       }
       
       String l_strDateOfYear = l_bornDate.substring(0,2);
       int    l_dateOfYear    = Integer.parseInt(l_strDateOfYear);

       if(WEB3EraBornDef.MEIJI.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_MEIJI;
       }
       else if(WEB3EraBornDef.TAISYO.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_TAISYO;
       }
       else if(WEB3EraBornDef.SYOWA.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_SYOWA;
       }
       else if(WEB3EraBornDef.HEISEI.equals(l_eraBorn))
       {
           l_dateOfYear = l_dateOfYear + AD_HEISEI;
       }
       else
       {
           l_dateOfYear = 0;
       }
       
       if ( l_dateOfYear == 0 )
       {
           l_strDateOfYear = "0000";
       }
       else
       {
           l_strDateOfYear = Integer.toString(l_dateOfYear);
       }

       return l_strDateOfYear + l_bornDate.substring(2);
   }
}
@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
d4 1
a4 1
Author Name         : Daiwa Institute of Research
d13 1
d205 6
@

