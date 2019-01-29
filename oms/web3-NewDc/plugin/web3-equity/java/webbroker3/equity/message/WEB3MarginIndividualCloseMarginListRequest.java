head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginIndividualCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ʌ��ψꗗ���N�G�X�g(WEB3MarginIndividualCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����ʌ��ψꗗ���N�G�X�g�j�B<br>
 * <br>
 * �M�p����ʌ��ψꗗ���N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginIndividualCloseMarginListRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginIndividualCloseMarginListRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_individualCloseMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    
    /**
     * (����id)<BR>
     */
    public String[] id;
    
    /**
     * @@roseuid 414047490371
     */
    public WEB3MarginIndividualCloseMarginListRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�h�c��null�ł������ꍇ�uID��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00081<BR>
     * �@@�P�|�Q�jthis.�h�c.�v�f�����O�ł������ꍇ�uID�̗v�f����0�v��<BR>
     * ��O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00282<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084945402AD
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "������ WEB3MarginIndividualCloseMarginListRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�h�c�`�F�b�N<BR>
        log.debug("this.�h�c��null�ł������ꍇ");
        // �@@�P�|�P�jthis.�h�c��null�ł������ꍇ�uID��null�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00081<BR>
        if (this.id == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00081,
            this.getClass().getName() + "validate");
        }

        log.debug("this.�h�c.�v�f�����O�ł������ꍇ");        
        // �@@�P�|�Q�jthis.�h�c.�v�f�����O�ł������ꍇ�uID�̗v�f����0�v��<BR>
        // ��O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00282<BR>
        // @@throws WEB3BaseException
        // @@roseuid 4084945402AD
        int l_intIDLength = this.id.length;
        if (l_intIDLength == 0)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00282,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414047490385
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginIndividualCloseMarginListResponse(this);
    }
}
@
