head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������ꗗ���N�G�X�g(WEB3AdminOffFloorProductListRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������ꗗ���N�G�X�g)<BR>
 * <BR>
 * �Ǘ��җ���O���������ꗗ�T�[�r�X�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * -----<English>-----------<BR>
 * <BR>
 * WEB3AdminOffFloorProductListRequest<BR>
 * <BR>
 * request data of WEB3AdminOffFloorProductListService<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_product_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorProductListRequest.class);

    /**
     * (�\�[�g�L�[)<BR>
     * <BR>
     * �Ǘ��җ���O�����\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * �Ώۍ��ځF�����R�[�h�A�s��R�[�h�A��t�J�n�����A��t�I������<BR>
     * <BR>
     * ----<English>------------<BR>
     * <BR>
     * sortKeys<BR>
     * <BR>
     * a list of sortKeys for administrator: off floor<BR>
     * <BR>
     * items: productCode, marketCode, orderStartDatetime, orderEndDatetime<BR>
     * <BR>
     */
    public webbroker3.eqtypeadmin.message.WEB3AdminOffFloorSortKey[] sortKeys;

    /**
     * @@roseuid 421AE24C01F7
     */
    public WEB3AdminOffFloorProductListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�\�[�g�L�[.�v�f�����O�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�@@�E�����R�[�h<BR>
     * �@@�@@�@@�@@�E�s��R�[�h<BR>
     * �@@�@@�@@�@@�E��t�J�n����<BR>
     * �@@�@@�@@�@@�E��t�I������<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)sortKeys check<BR>
     * �@@1-1)If this.sorKeys��null<BR>
     * �@@�@@�@@�@@Throw the exception "sortKeys is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@1-2)If the number of elements of this.sortKeys = 0,<BR>
     * �@@�@@�@@�@@Throw the exception "the number of elements of this.sortKeys is 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@1-3)Check the followings about all elements of this.sortKeys<BR>
     * <BR>
     * �@@�@@1-3-1)Call sortKeys.validate()<BR>
     * <BR>
     * �@@�@@1-3-2)Repeat the check for as many times as the number of arrays of
     * sortKeys<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@If there is an item name other than the following item
     * names<BR>
     *                     Throw an exception<BR>
     * �@@�@@�@@�@@�EproductCode<BR>
     * �@@�@@�@@�@@�EmarketCode<BR>
     * �@@�@@�@@�@@�EorderStartDatetime<BR>
     * �@@�@@�@@�@@�EorderEndDatetime<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * �V�X�e�����ʁiweb3-common�j.(web3)�V�X�e�������N���X_common.WEB3BaseException
     * @@roseuid 41BD053003A5
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_intSortKeyLength = 0;

        //1-1) If this.sorKeys��null, throw Exception.
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        /*
         * 1-2) If the number of elements of this.sortKeys = 0,<BR>
         * throw the exception
         */
        l_intSortKeyLength = sortKeys.length;
        if (l_intSortKeyLength == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 1-3-1) Call sortKeys.validate()
        } else
        {
            for (int i = 0; i < l_intSortKeyLength; i++)
            {
                sortKeys[i].validate();
            }

            /* 1-3-2)Repeat the check for as many times as the number of arrays of
            * sortKeys
            *  If there is an item name other than the following item
            * names  Throw an exception
            * productCode
            * marketCode
            * orderStartDatetime
            * orderEndDatetime
            **/
            for (int i = 0; i < l_intSortKeyLength; i++)
            {
                if ((!"productCode".equals(sortKeys[i].keyItem))
                    && (!"marketCode".equals(sortKeys[i].keyItem))
                    && (!"orderStartDatetime".equals(sortKeys[i].keyItem))
                    && (!"orderEndDatetime".equals(sortKeys[i].keyItem)))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminOffFloorProductListResponse(this);
    }
}
@
