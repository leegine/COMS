head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������ڍ׏��(WEB3FeqExecuteDetailInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[ 
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.464  
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O���������ڍ׏��)<BR>
 * �O���������ڍ׏��N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqExecuteDetailInfoUnit extends Message 
{
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��<BR>
     */
    public Date localDeliveryDate;
    
    
    /**
     * (��萔��)<BR>
     * ��萔��<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;
    
    /**
     * (�����)<BR>
     * �����<BR>
     */
    public String execAmount;
    
    /**
     * (����ԋ敪)<BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F�����<BR>
     * 1�F�ꕔ����<BR>
     * 2�F�S������<BR>
     * 3�F��菈����(�ꕔ����)<BR>
     * 4�F��菈����(�S������)<BR>
     */
    public String execType;
    
    /**
     * (��n���)<BR>
     * ��n���<BR>
     */
    public String deliveryPrice;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;  
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (���n�萔��)<BR>
     * ���n�萔��<BR>
     */
    public String localCommission;
    
    /**
     * (���n�����)<BR>
     * ���n�����<BR>
     */
    public String localTradingTax;
    
    /**
     * (���̑��R�X�g�P)<BR>
     * ���̑��R�X�g�P<BR>
     */
    public String otherCost1;
    
    /**
     * (���̑��R�X�g�Q)<BR>
     * ���̑��R�X�g�Q<BR>
     */
    public String otherCost2;
    
    /**
     * (���Z���)<BR>
     * ���Z���<BR>
     */
    public String clearUpPrice;
    
    /**
     * (���Z����i�O�݁j)<BR>
     * ���Z����i�O�݁j<BR>
     */
    public String foreignClearUpPrice;
    
    /**
     * (�����萔��)<BR>
     * �����萔��<BR>
     */
    public String commission;
    
    /**
     * (�����萔�������)<BR>
     * �����萔�������<BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * (�����萔���i�O�݁j)<BR>
     * �����萔���i�O�݁j<BR>
     */
    public String foreignCommission;
    
    /**
     * (�����萔������Łi�O�݁j)<BR>
     * �����萔������Łi�O�݁j<BR>
     */
    public String foreignCommissionConsumptionTax;
    
    /**
     * (��薾�׈ꗗ)<BR>
     * �O���������ڍׁi�Ǘ��ҁj�̔z��<BR>
     */
    public WEB3FeqExecDetailInfoUnit[] execDetailList;
    
    /**
     * (���בփ��[�g)<BR>
     * ���בփ��[�g<BR>
     */
    public String execExchangeRate;
    
    /**
     * (�O���������ڍ׏��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42A537780118
     */
    public WEB3FeqExecuteDetailInfoUnit() 
    {
     
    }
}
@
