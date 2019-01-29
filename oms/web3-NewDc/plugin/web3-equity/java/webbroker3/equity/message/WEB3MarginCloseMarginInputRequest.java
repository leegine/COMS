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
filename	WEB3MarginCloseMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����ԍϒ������̓��N�G�X�g�N���X(WEB3MarginCloseMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����ԍϒ������̓��N�G�X�g�j�B<br>
 * <br>
 * �M�p����ԍϒ������̓��N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCloseMarginInputRequest.class);    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101800L;        
    /**
     * (����ID)<BR>
     */
    public String[] id;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �Ώۍ��ځF�����A���v<BR>
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (��������)<BR>
     * �ꊇ�ԍϒP�ʂ̍��v��������<BR>
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 4140485A0002
     */
    public WEB3MarginCloseMarginInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�h�c��null�ł������ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�h�c.�v�f�����O�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�uID�̗v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �Q�j�@@�M�p����\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�M�p����\�[�g�L�[��null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�M�p����\�[�g�L�[��null�@@���A<BR>
     * �@@�@@�@@�@@this.�M�p����\�[�g�L�[.�v�f�����O�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�M�p����\�[�g�L�[�̑S�v�f�ɂ��Ĉȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j�M�p����\�[�g�L�[.�L�[���ڂ��ȉ��̍��ڈȊO�̒l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h���P���h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00615<BR>
     * <BR>
     * �R�j�@@���������`�F�b�N<BR>
     * �@@�@@this.����������null�A���� this.�����������O�ł������ꍇ�A<BR>
     * �@@�@@�u����������0�ȉ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 408665480224
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // �P�j�@@�h�c�`�F�b�N<BR>
        //   * �@@�P�|�P�jthis.�h�c��null�ł������ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
        //   *   class: WEB3BusinessLayerException<BR>
        //   *   tag:   BUSINESS_ERROR_00080<BR>
        //
        if (id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        // �@@�P�|�Q�jthis.�h�c.�v�f�����O�ł������ꍇ�A<BR>
        //     * �@@�@@�@@�@@�@@�uID�̗v�f����0�v�̗�O���X���[����B<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00080<BR>
        //
        if (id.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        // �Q�j�@@�M�p����\�[�g�L�[�`�F�b�N<BR>
        //    * �@@�Q�|�P�jthis.�M�p����\�[�g�L�[��null�ł������ꍇ�A<BR>
        //    * �@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00231<BR>
        //
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,STR_METHOD_NAME);
        }
        // �@@�Q�|�Q�jthis.�M�p����\�[�g�L�[��null�@@���A<BR>
        //    * �@@�@@�@@�@@this.�M�p����\�[�g�L�[.�v�f�����O�ł������ꍇ�A<BR>
        //    * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00232<BR>
        //
        if (sortKeys != null && sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,STR_METHOD_NAME); 
        }
        // �@@�Q�|�R�jthis.�M�p����\�[�g�L�[�̑S�v�f�ɂ��Ĉȉ��̃`�F�b�N���s���B<BR>
        //     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
        //     * <BR>
        //     * �@@�@@�Q�|�R�|�Q�j�M�p����\�[�g�L�[.�L�[���ڂ��ȉ��̍��ڈȊO�̒l<BR>
        //     * �@@�@@�@@�@@�@@�@@�@@�@@�ł������ꍇ�A<BR>
        //     * �@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
        //     * �@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
        //     * �@@�@@�@@�@@�@@�@@�@@�E�h���P���h<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00086<BR>
        //
        for (int i = 0; i < sortKeys.length;i++)
        {
            sortKeys[i].validate();   
            if (!WEB3EquityKeyItemDef.OPEN_DATE.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(sortKeys[i].keyItem)) 
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,STR_METHOD_NAME); 
            }
        }
        /* �R�j�@@���������`�F�b�N<BR>
            * �@@�@@this.����������null�A���� this.�����������O�ł������ꍇ�A<BR>
            * �@@�@@�u����������0�ȉ��v�̗�O���X���[����B<BR>
            *   class: WEB3BusinessLayerException<BR>
            *   tag:   BUSINESS_ERROR_00126<BR>
        */
        if (orderQuantity != null && Long.parseLong(orderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,STR_METHOD_NAME); 
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * @@return WEB3GenResponse<BR>
     * @@roseuid 4140485A002A
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3MarginCloseMarginInputResponse(this);
    }
}
@
