head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListSearchConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������ꗗ��������(WEB3AdminBondDomesticProductListSearchConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ���n�m (���u) �V�K�쐬 ���f��203
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҍ����������ꗗ��������)<BR>
 * �Ǘ��ҍ����������ꗗ��������<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListSearchConditionUnit extends Message
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListSearchConditionUnit.class);

    /**
     * (���^�C�v)<BR>
     * ���^�C�v<BR>
     * <BR>
     * 11:�l��������<BR>
     * 12:�Ѝ�<BR>
     */
    public String bondType;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�񍆃R�[�h)<BR>
     * �񍆃R�[�h<BR>
     * <BR>
     * ���j'1'�����͂��ꂽ�ꍇ�A��ʂŁA'00001'�̂悤��0���l�߂�5���ŃZ�b�g����B<BR>
     */
    public String productIssueCode;

    /**
     * (�������iHOST�j)<BR>
     * �������iHOST�j<BR>
     */
    public String productNameHost;

    /**
     * (�������iWEB3�j)<BR>
     * �������iWEB3�j<BR>
     */
    public String productNameWEB3;

    /**
     * (���s��)<BR>
     * ���s��<BR>
     */
    public Date issueDate;

    /**
     * (���ғ�)<BR>
     * ���ғ�<BR>
     */
    public Date maturityDate;

    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �fMMDD�f����<BR>
     */
    public String interestPaymentDay;

    /**
     * (�戵�敪)<BR>
     * �戵�敪<BR>
     * <BR>
     * 0�F�s��<BR>
     * 2�F�ڋq<BR>
     */
    public String tradeHandleDiv;

    /**
     * @@roseuid 4691C5EC016F
     */
    public WEB3AdminBondDomesticProductListSearchConditionUnit()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���^�C�v�`�F�b�N<BR>
     * �@@�@@���^�C�v �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * �@@�@@�@@�E���^�C�v���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�f�l�������f<BR>
     * �@@�@@�@@�@@�@@�@@�f�Ѝf<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02869<BR>
     * <BR>
     * �Q�j�戵�敪�`�F�b�N<BR>
     * �@@�@@�戵�敪 �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * �@@�@@�@@�E�戵�敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�f�s�f<BR>
     * �@@�@@�@@�@@�@@�@@�f�ڋq�f<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02844<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4684C09F0366
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���^�C�v�`�F�b�N
        //�@@�@@���^�C�v �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        //�@@�@@�@@�E���^�C�v���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B
        //�@@�@@�@@�@@�@@�@@�f�l�������f
        //�@@�@@�@@�@@�@@�@@�f�Ѝf
        if (this.bondType != null)
        {
            if (!String.valueOf(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND.intValue()).equals(this.bondType)
                && !String.valueOf(BondTypeEnum.CORPORATE_BOND.intValue()).equals(this.bondType))
            {
                log.debug("���^�C�v�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02869,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���^�C�v�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //�Q�j�戵�敪�`�F�b�N
        //�@@�@@�戵�敪 �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        //�@@�@@�@@�E�戵�敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B
        //�@@�@@�@@�@@�@@�@@�f�s�f
        //�@@�@@�@@�@@�@@�@@�f�ڋq�f
        if (this.tradeHandleDiv != null)
        {
            if (!WEB3TradeHandleDivDef.DISABLED.equals(this.tradeHandleDiv)
                && !WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(this.tradeHandleDiv))
            {
                log.debug("�戵�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02844,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�戵�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
