head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����O���������ꗗ���N�G�X�g(WEB3EquityOffFloorProductListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �����a��(SRA) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i����O���������ꗗ���N�G�X�g�j�B<BR>
 * <BR>
 * ����O���������ꗗ�v���@@���N�G�X�g�f�[�^�N���X
 * @@author �����a��(SRA) 
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListRequest extends WEB3GenRequest
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOffFloorProductListRequest.class);
        
    /**
     * �iserialVersionUID�j�B
     */
    public static final long serialVersionUID = 200412171000L;

    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "equity_off_floor_product_list";
    
    /**
     * �i�\�[�g�L�[�j�B<BR>
     * <BR>
     * ���������\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * �Ώۍ��ځF�����R�[�h�A�s��R�[�h�A��t�J�n�����A��t�I������
     */
    public WEB3EquitySortKey[] sortKeys;

    /**
     * �i�f�t�H���g�R���X�g���N�^�j�B
     */
    public WEB3EquityOffFloorProductListRequest()
    {
    }
    
    /**
     * �icreate����O���������ꗗ���X�|���X�j�B
     * ����O���������ꗗ���X�|���X�𐶐�����B
     * @@return WEB3EquityCommonInputResponse
     * @@roseuid 4063B6D90191
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityOffFloorProductListResponse(this);
    }

    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�\�[�g�L�[.�v�f�����O�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
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
     * �@@�@@�@@�@@�E��t�I������
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�\�[�g�L�[�̃`�F�b�N");
        if(this.sortKeys == null)
        {
            // �\�[�g�L�[��null�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + ".validate()");
        }
        
        int l_intSortKeysCount = this.sortKeys.length;
        
        if(l_intSortKeysCount == 0)
        {
            // �\�[�g�L�[.�v�f�����O�������ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + ".validate()");
        }
        
        for(int i = 0 ; i < l_intSortKeysCount ; i++)
        {
            this.sortKeys[i].validate();
            
            if(!(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                || WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                || WEB3EquityKeyItemDef.ORDER_START_DATE_TIME.equals(this.sortKeys[i].keyItem)
                || WEB3EquityKeyItemDef.ORDER_END_DATE_TIME.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01698,
                    this.getClass().getName() + ".validate()");
            }
            
        }
        
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
