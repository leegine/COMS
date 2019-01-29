head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������ʖ���(WEB3FeqOrderCommonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�O�������������ʖ���)<BR>
 * �O�������������ʖ��׃N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqOrderCommonUnit extends Message 
{
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String orderId;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h
     */
    public String managementCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * �i�����ԍ��j
     */
    public String requestNumber;
    
    /**
     * (�`�[�ԍ�)<BR>
     * �`�[�ԍ�
     */
    public String orderNumber;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����
     */
    public String taxType;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F���t<BR>
     * 2�F���t 
     */
    public String dealingType;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~�݌���<BR>
     * 1�F�O�݌���
     */
    public String settleDiv;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F������<BR>
     * 3�F��t<BR>
     * 4�F����<BR>
     * 7�F�s�o���������s
     */
    public String execCondType;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     * <BR>
     * ���������蒍���̏ꍇ�́Anull�B
     */
    public Date expirationDate;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l
     */
    public String orderCondType;
    
    /**
     * (���������P��)<BR>
     * ���������P��<BR>
     * <BR>
     * �������������h�t�w�l�h�������́hW�w�l�h�̏ꍇ�A�ݒ肳���B
     */
    public String orderCondPrice;
    
    /**
     * (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�<BR>
     * 2�F�ȉ�<BR>
     * <BR>
     * �������������h�t�w�l�h�������́hW�w�l�h�̏ꍇ�A�ݒ肳���B
     */
    public String condOperator;
    
    /**
     * (W�w�l�p�����P���敪)<BR>
     * W�w�l�p�����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ肳���B
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W�w�l�p�����P��)<BR>
     * W�w�l�p�����P��<BR>
     * <BR>
     * ��W�w�l�p�����P���敪���h�w�l�h�̏ꍇ�A�ݒ肳���B
     */
    public String wLimitPrice;
    
    /**
     * (��������)<BR>
     * ��������
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��
     */
    public String orderPrice;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���
     */
    public String estimatedPrice;
    
    /**
     * (�T�Z��n����i�O�݁j)<BR>
     * �T�Z��n����i�O�݁j
     */
    public String foreignEstimatedPrice;
    
    /**
     * (��������)<BR>
     * ��������
     */
    public Date orderDate;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (�ڋq�敪)<BR>
     * �ڋq�敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@���Ǝ�<BR>
     * 2�F�@@����<BR>
     */
    public String accountDiv;
    
    /**
     * (�O�������������ʖ���)<BR>
     * �R���X�g���N�^
     * @@roseuid 4282F6EE0134
     */
    public WEB3FeqOrderCommonUnit() 
    {
     
    }
}
@
