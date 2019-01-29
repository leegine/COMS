head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�ʃ��N�G�X�g(WEB3BondBalanceReferenceCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 ���g (���u) �V�K�쐬 ���f��206
*/
package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondBalanceReferenceTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���c���Ɖ�ʃ��N�G�X�g)<BR>
 * ���c���Ɖ�ʃ��N�G�X�g�N���X
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceCommonRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707171900L;

    /**
     * (�Ɖ�敪)<BR>
     * �Ɖ�敪<BR>
     * <BR>
     * �P�F���ׂĂ̖���<BR>
     * �Q�F�O���������̂�<BR>
     * �R�F�������i�l���������܂ށj<BR>
     * �S�F�������i�l���������܂܂Ȃ��j<BR>
     * �T�F�l�������̂�<BR>
     * <BR>
     * ��null�̏ꍇ�A�u1�F���ׂĂ̖����v�Ƃ���B<BR>
     */
    public String referenceType;

    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceCommonRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }

    /**
     * ���N���X�̐������`�F�b�N���s��<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�Ɖ�敪�̃`�F�b�N <BR>
     * �@@this.�Ɖ�敪��null�̏ꍇ�A��`�l�̃`�F�b�N���s���B<BR>
     * �@@�@@this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�u�Ɖ�敪������`�l�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�P�F���ׂĂ̖���<BR>
     * �@@�@@�@@�@@�Q�F�O���������̂�<BR>
     * �@@�@@�@@�@@�R�F�������i�l���������܂ށj<BR>
     * �@@�@@�@@�@@�S�F�������i�l���������܂܂Ȃ��j<BR>
     * �@@�@@�@@�@@�T�F�l�������̂�<BR>
     * <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00082<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //this.�Ɖ�敪��null�łȂ��ꍇ�A��`�l�̃`�F�b�N���s���B
        if (this.referenceType != null)
        {
            if ((!WEB3BondBalanceReferenceTypeDef.ALL_PRODUCT.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.FOREIGN_BOND_ONLY.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_INDIVIDUAL.equals(this.referenceType)))
            {
                log.debug(STR_METHOD_NAME + "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        else
        {
            //��null�̏ꍇ�A�u1�F���ׂĂ̖����v�Ƃ���B
            this.referenceType = WEB3BondBalanceReferenceTypeDef.ALL_PRODUCT;
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
