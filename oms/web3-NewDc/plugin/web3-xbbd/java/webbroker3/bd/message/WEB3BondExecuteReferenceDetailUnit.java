head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ɖ��(WEB3BondExecuteReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ���� (���u) �V�K�쐬
Revesion History : 2007/07/09 ���n�m (���u) ���f��No.191
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���������Ɖ��)<BR>
 * ���������Ɖ��
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceDetailUnit extends Message
{
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String bondCategCode;
    
    /**
     * (����敪)<BR>
     * ����敪 <BR>
     * <BR>
     * 1�F���� <BR>
     * 2�F���t <BR>
     * 3�F���p<BR>
     */
    public String stateDiv;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪 <BR>
     * <BR>
     * 1�F�~�� <BR>
     * 2�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (�z�ʋ��z)<BR>
     * �z�ʋ��z<BR>
     */
    public String faceAmount;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String orderPrice;
    
    /**
     * (��������i�~�݁j)<BR>
     * ��������i�~�݁j<BR>
     */
    public String yenTradePrice;
    
    /**
     * (�o�ߗ��q�i�~�݁j)<BR>
     * �o�ߗ��q�i�~�݁j<BR>
     */
    public String yenAccruedInterest;
    
    /**
     * (��n����i�~�݁j)<BR>
     * ��n����i�~�݁j<BR>
     */
    public String yenDeliveryPrice;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     */
    public String fxRate;
    
    /**
     * (��������i�O�݁j)<BR>
     * ��������i�O�݁j<BR>
     */
    public String foreignTradePrice;
    
    /**
     * (�o�ߗ��q�i�O�݁j)<BR>
     * �o�ߗ��q�i�O�݁j<BR>
     */
    public String foreignAccruedInterest;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public Date orderDate;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date domesticExecutionDate;
    
    /**
     * (���n����)<BR>
     * ���n����<BR>
     */
    public Date foreignExecutionDate;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date domesticDeliveryDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��<BR>
     */
    public Date foreignDeliveryDate;
    
    /**
     * (�������)<BR>
     * ������� <BR>
     * <BR>
     * 0�F�����i�����ρj<BR>
     * 1�F����<BR>
     * 2�F�����<BR>
     */
    public String executionState;
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪 <BR>
     * <BR>
     * 1�F�R�[���Z���^�[ <BR>
     * 2�F�o�b <BR>
     * 3:�X�����O�V���b�g <BR>
     * 4�Fi-mode <BR>
     * 5�FVodafone <BR>
     * 6�FAU <BR>
     * 9�FHOST <BR>
     * A�F�Ǘ���<BR>
     */
    public String orderRootDiv;
    
    /**
     * (�����`���l��)<BR>
     * �����`���l�� <BR>
     * <BR>
     * 0�F�c�ƓX <BR>
     * 1�F�C���^�[�l�b�g <BR>
     * 2�F�R�[���Z���^ <BR>
     * 3�F���o�C��<BR>
     */
    public String orderChannel;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h<BR>
     */
    public String operatorCode;
    
    /**
     * (����\�t���O)<BR>
     * ����\�t���O <BR>
     * <BR>
     * true�F����\ <BR>
     * false�F����s��<BR>
     */
    public boolean cancelDiv;
    
    /**
     * (���������Ɖ��)<BR>
     * �f�t�H���g�R���X�g���N�^<BR> 
     */
    public WEB3BondExecuteReferenceDetailUnit()
    {
        
    }
}
@
