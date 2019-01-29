head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������������(WEB3FeqExecuteGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O��������������)<BR>
 * �O�������������׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqExecuteGroup extends Message 
{
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String id;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����<BR>
     */
    public String taxType;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F���t<BR>
     * 2�F���t<BR>
     */
    public String dealingType;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F������<BR>
     * 3�F��t<BR>
     * 4�F����<BR>
     * 7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     */
    public Date expirationDate;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public Date orderDate;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     * <BR>
     * ��0�̏ꍇ�́A���s<BR>
     */
    public String limitPrice;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
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
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (�T�Z��n����i�O�݁j)<BR>
     * �T�Z��n����i�O�݁j<BR>
     */
    public String foreignEstimatedPrice;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �������i�̒������Ɖ�ɂ���u�����󋵁v�ɏ����B<BR>
     */
    public String transactionStateType;
    
    /**
     * (�����\�t���O)<BR>
     * �����\�t���O<BR>
     * <BR>
     * true�F�����\<BR>
     * false�F�����s��<BR>
     */
    public boolean changeFlag;
    
    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     * <BR>
     * true�F����\<BR>
     * false�F����s��<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (�����`���l��)<BR>
     * �����`���l��<BR>
     * ���R�[���Z���^�[�̏ꍇ�̂ݕ\��<BR>
     * <BR>
     * 0:�c�ƓX<BR>
     * 1�F�C���^�[�l�b�g<BR>
     * 2�F�R�[���Z���^�[<BR>
     * 3�F���o�C��<BR>
     */
    public String orderChannel;
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * ���R�[���Z���^�[�̏ꍇ�̂ݕ\��<BR>
     * <BR>
     * 1�F�R�[���Z���^�[<BR>
     * 2�F�o�b<BR>
     * 3�F�X�����O�V���b�g<BR>
     * 4�Fi-mode<BR>
     * 5�FVodafone<BR>
     * 6�FAU<BR>
     * 9�FHOST<BR>
     * A�F�Ǘ���<BR>
     */
    public String orderRootDiv;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     * ���R�[���Z���^�[�̏ꍇ�̂ݕ\��<BR>
     */
    public String traderCode;
    
    /**
     * (�������ꗗ)<BR>
     * �O������������薾�ׂ̔z��<BR>
     */
    public WEB3FeqExecuteUnit[] executeUnits;
    
    /**
     * (�O��������������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 420766AC006D
     */
    public WEB3FeqExecuteGroup() 
    {
     
    }
}
@
