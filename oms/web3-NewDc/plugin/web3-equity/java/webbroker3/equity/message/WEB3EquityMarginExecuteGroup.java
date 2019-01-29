head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�������Ɖ���P��(WEB3EquityMarginExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �֔�(���u) �V�K�쐬
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1164
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����E�M�p�������Ɖ���P��)<BR>
 * �����E�M�p�������Ɖ���P�ʃN���X<BR>
 * @@author  �֔�
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteGroup extends Message
{

    /**
     * (ID)<BR>
     * ����ID<BR>
     */
    public String id;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String taxType;

    /**
     * (�����敪�i�����E���n�j)<BR>
     * �����敪�i�����E���n�j<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String swapTaxType;

    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String tradingType;

    /**
     * (�ٍ�)<BR>
     * �ٍ�<BR>
     */
    public WEB3MarginRepaymentUnit repayment;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date openDate;

    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String contractPrice;

    /**
     * (�l�i����)<BR>
     * �l�i����<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String priceCondType;

    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orderCondType;

    /**
     * (�t�w�l�p���������P��)<BR>
     * �t�w�l�p���������P��<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * �t�w�l�p�����������Z�q<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (�v�w�l�p���������P��)<BR>
     * �v�w�l�p���������P��<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * �v�w�l�p�����������Z�q<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (�v�w�l�p�����P���敪)<BR>
     * �v�w�l�p�����P���敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (�v�w�l�p�����P��)<BR>
     * �v�w�l�p�����P��<BR>
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * �v�w�l�p���s����<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String wlimitExecCondType;

    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * �v�w�l�p�L����ԋ敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * �v�w�l�p�֑ؑO�����P��<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * �v�w�l�p�֑ؑO���s����<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (�����������敪)<BR>
     * �����������敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orgOrderCondType;

    /**
     * (�����������P��)<BR>
     * �����������P��<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (�������������Z�q)<BR>
     * �������������Z�q<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orgOrderCondOperator;

    /**
     * (���v�w�l�p�����P���敪)<BR>
     * ���v�w�l�p�����P���敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (���v�w�l�p�����P��)<BR>
     * ���v�w�l�p�����P��<BR>
     */
    public String orgWlimitPrice;

    /**
     * (���v�w�l�p���s����)<BR>
     * ���v�w�l�p���s����<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;

    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String limitPrice;

    /**
     * (��芔��)<BR>
     * ��芔��<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;

    /**
     * (�T�Z��n���)<BR>
     * �ʏ프���A����O�����A�������n �F �T�Z��n���<BR>
     * �V�K�� �F �T�Z�����<BR>
     * �ԍ� �F �T�Z���ϑ��v���<BR>
     */
    public String estimatedPrice;

    /**
     * (��n���)<BR>
     * �ʏ프���A����O�����A�������n �F ��n���<BR>
     * �V�K�� �F �����<BR>
     * �ԍ� �F ���ϑ��v���<BR>
     */
    public String deliveryPrice;

    /**
     * (�T�Z���v)<BR>
     * �T�Z���v<BR>
     */
    public String estimatedProfitLoss;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public Date orderDate;

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
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String transactionStateType;

    /**
     * (�����\�t���O)<BR>
     * �����\�t���O<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public boolean changeFlag;

    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public boolean cancelFlag;

    /**
     * (�����`���l��)<BR>
     * �����`���l��<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orderChannel;

    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String orderRootDiv;

    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h<BR>
     */
    public String operatorCode;

    /**
     * (�������ꗗ)<BR>
     * �������ɕR�t���������̈ꗗ<BR>
     */
    public webbroker3.equity.message.WEB3EquityMarginExecuteUnit[] executeUnits;

    /**
     * (�x���敪)<BR>
     * �x���敪<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public String delayDiv;

    /**
     * (�蓮�����\�t���O)<BR>
     * �蓮�����\�t���O<BR>
     * <BR>
     * �ݒ�l�ɂ��ẮA���b�Z�[�W��`���Q�ƁB<BR>
     */
    public boolean manualFlag;

    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     * <BR>
     * 0�F�@@���ϊ�������<BR>
     * 1�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j<BR>
     * 2�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j<BR>
     * 3�F�@@�ۏ؋��ێ������i��ԁj<BR>
     * 9�F�@@�蓮��������<BR>
     * <BR>
     * ���������ϒ����łȂ��ꍇ��null���Z�b�g�����B<BR>
     */
    public String forcedSettleReason = null;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�@@�I�[�v��<BR>
     * 1�F�@@����������<BR>
     */
    public String forcedLapseDiv;

    /**
     * (�����E�M�p�������Ɖ���P��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 455C3B6301D6
     */
    public WEB3EquityMarginExecuteGroup()
    {

    }
}
@
