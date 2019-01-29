head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSOrderDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE����(PTS)�����ڍ�Unit�iWEB3AdminEquityPTSOrderDetailUnit.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��186
 Revision History : 2008/02/27 ��іQ (���u) �d�l�ύX ���f��199
 */
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ��ҁE�����iPTS�j�����ڍ�Unit)<BR>
 * �Ǘ��ҁE�����iPTS�j�����ڍ�Unit�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSOrderDetailUnit extends Message
{
    /**
     * (����ID)<BR>
     */
    public String orderId;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�����R�[�h)<BR>
     */
    public String productCode;

    /**
     * (�s��R�[�h)<BR>
     */
    public String marketCode;

    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (���i�敪)<BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     */
    public String productDiv;

    /**
     * (����敪)<BR>
     * 1�F�@@�������t���� <BR>
     * 2�F�@@�������t���� <BR>
     * 3�F�@@�V�K�������� <BR>
     * 4�F�@@�V�K�������� <BR>
     * 5�F�@@�����ԍϒ��� <BR>
     * 6�F�@@�����ԍϒ��� <BR>
     * 7�F�@@�������� <BR>
     * 8�F�@@���n���� <BR>
     */
    public String tradingType;

    /**
     * (�ٍϋ敪)<BR>
     * 1�F�@@���x�M�p <BR>
     * 2�F�@@��ʐM�p <BR>
     * <BR>
     * ���M�p�����̏ꍇ�Z�b�g����B<BR>
     */
    public String repaymentDiv = null;

    /**
     * (���s����)<BR>
     * 1�F�������@@3�F��t�@@4�F�����@@7�F�s�o���������s<BR>
     */
    public String execCondType;

    /**
     * (�����L������)<BR>
     */
    public Date expirationDate = null;

    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l <BR>
     * 7:���s�c����� <BR>
     */
    public String priceCondType;

    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l <BR>
     */
    public String orderCondType;

    /**
     * (��������)<BR>
     */
    public String orderQuantity;

    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     */
    public String limitPrice = null;

    /**
     * (��萔��)<BR>
     */
    public String execQuantity = null;

    /**
     * (���P��)<BR>
     */
    public String execPrice = null;

    /**
     * (������ԋ敪)<BR>
     * 0�F���̑� 1�F��t�ρi�V�K�����j 2�F�������i�V�K�����j 3�F�����ρi�V�K�����j<BR>
     * 6�F�������s�i�V�K�����j 7�F��t�ρi�ύX�����j 8�F�������i�ύX�����j<BR>
     * 10�F�����ρi�ύX�����j 11�F�������s�i�ύX�����j 12�F��t�ρi��������j<BR>
     * 13�F�������i��������j 14�F�����ρi��������j 15�F�������s�i��������j<BR>
     * 20�F�ꕔ���� 21�F�S������ 22�F���� 23�F�蓮���� <BR>
     * 24:�ؑ֒��� 25:�ؑ֎�t 26:�ؑ֊��� 27:�ؑ֒���(���s) <BR>
     * 50�F�J�z�� 51�F�J�z���s<BR>
     */
    public String orderState;

    /**
     * (����ԋ敪)<BR>
     * 0�F�@@����� <BR>
     * 1�F�@@�ꕔ���� <BR>
     * 2�F�@@�S������ <BR>
     */
    public String execType;

    /**
     * (��������敪)<BR>
     * 0�F�@@�����l <BR>
     * 1�F�@@����� <BR>
     * 2�F�@@�ꕔ�������<BR>
     * 3�F�@@�S��������� <BR>
     * 4�F�@@������s <BR>
     * 5�F�@@������ <BR>
     * 6�F�@@�ꕔ��������<BR>
     * 7�F�@@�S���������� <BR>
     * 8�F�@@�������s <BR>
     * 9�F�@@�G���[ <BR>
     * A�F�@@W�w�l�����ؑ֒� <BR>
     * B�F�@@W�w�l�����ꕔ�ؑ֊���<BR>
     * C�F�@@W�w�l�����S���ؑ֊��� <BR>
     * D�F�@@W�w�l�����ؑ֎��s <BR>
     */
    public String changeCancelDiv;

    /**
     * (��������)<BR>
     */
    public Date orderDate;

    /**
     * (������)<BR>
     */
    public Date orderBizDate;

    /**
     * (��n��)<BR>
     */
    public Date deliveryDate;

    /**
     * @@roseuid 4795A0F902F2
     */
    public WEB3AdminEquityPTSOrderDetailUnit()
    {

    }
}
@
