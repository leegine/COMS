head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������̓��X�|���X(WEB3FeqChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�O�������������̓��X�|���X)<BR>
 * �O�������������̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqChangeInputResponse extends WEB3FeqInputCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_changeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
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
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (���o������)<BR>
     * ���o������<BR>
     */
    public String partContQuantity;
    
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
     * �������P���敪���h�w�l�h�̏ꍇ�A�ݒ�B<BR>
     */
    public String limitPrice;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F�����Ȃ�<BR>
     * 3�F��t<BR>
     * 4�F����<BR>
     * 7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 1�F��������<BR>
     * 2�F�o����܂Œ���<BR>
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     * <BR>
     * �����������敪���h�o����܂Œ����h�̏ꍇ�A�ݒ�<BR>
     */
    public Date expirationDate;
    
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
     * (�t�w�l�p���������P��)<BR>
     * �t�w�l�p���������P��<BR>
     * <BR>
     * �������������h�t�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * �t�w�l�p�����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�<BR>
     * 2�F�ȉ�<BR>
     * <BR>
     * �������������h�t�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (W�w�l�p���������P��)<BR>
     * W�w�l�p���������P��<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (W�w�l�p�����������Z�q)<BR>
     * W�w�l�p�����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�<BR>
     * 2�F�ȉ�<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W�w�l�p�����P���敪)<BR>
     * W�w�l�p�����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W�w�l�p�����P��)<BR>
     * W�w�l�p�����P��<BR>
     * <BR>
     * ��W�w�l�p�����P���敪���h�w�l�h�̏ꍇ�A�ݒ�B<BR>
     */
    public String wLimitPrice;
    
    /**
     * (���t�\���z)<BR>
     * ���t�\���z<BR>
     * <BR>
     * �����t�̏ꍇ�̂ݐݒ�<BR>
     * �����ϋ敪�ɂ��A�~��/�O�݂̂����ꂩ�̒l���ݒ肳���B<BR>
     */
    public String tradingPower;
    
    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P��<BR>
     * <BR>
     * �����t�̏ꍇ�̂ݐݒ�<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * <BR>
     * A0�FUSD A3�FHKD Z4�FEUR<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�Z�b�g<BR>
     */
    public String currencyCode;
    
    /**
     * @@roseuid 42CE3A070196
     */
    public WEB3FeqChangeInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
