head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t����o�^���N�G�X�g(WEB3DocumentDeliverHistoryRegistRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 ���V��@@(SRA) �V�K�쐬
Revesion History : 2008/01/23 鰃L��(���u) �d�l�ύX ���f��310
*/
package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ʌ�t����o�^���N�G�X�g)<BR>
 * <BR>
 * ���ʌ�t����o�^���N�G�X�g�N���X<BR>
 * @@author ���V��@@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistRequest extends WEB3GenRequest 
{
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "document_deliver_history_regist";
    
    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200709281829L;
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3DocumentDeliverHistoryRegistRequest.class);
    
    /**
     * �����R�[�h<BR>
     * ���M�AIPO�̏ꍇ�A�����R�[�h <BR>
     * ���O�C�����̏ꍇ�̂݁A�����̏ꍇ���� <BR>
     */
    public String[] productCode;
    
    /**
     * �d�q���`�F�b�N�t���O<BR>
     * true�F�`�F�b�N�v<BR>
     * false�F�`�F�b�N�s�v<BR>
     */
    public boolean eleBatoCheckFlg;
    
    /**
     * ��ʃR�[�h
     */
    public String typeCode;
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �ȉ��̍��ڂ̂����ꂩ��null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�����R�[�h�A��ʃR�[�h<BR>
     * <BR>
     * �����R�[�h��null�̏ꍇ<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_00079<BR>
     * <BR>
     * ��ʃR�[�h��null�̏ꍇ<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_02202<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        
        log.entering(STR_METHOD_NAME);

        // �����R�[�h��null�̏ꍇ
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����R�[�h�����w��ł��B");
        }
        if (this.productCode.length  == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����R�[�h�����w��ł��B");            
        }    

        // ��ʃR�[�h��null�̏ꍇ
        if (this.typeCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02202, 
                getClass().getName() + "." + STR_METHOD_NAME, 
                "��ʃR�[�h��null�ł���");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3DocumentDeliverHistoryRegistResponse(this);
    }

}
@
