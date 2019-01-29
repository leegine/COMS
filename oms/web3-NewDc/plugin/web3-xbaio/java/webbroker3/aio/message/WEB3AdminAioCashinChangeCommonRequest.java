head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�������ʃ��N�G�X�g�N���X(WEB3AdminAioCashinChangeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 ���r (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����ʒm�������ʃ��N�G�X�g)<BR>
 * �����ʒm�������ʃ��N�G�X�g�N���X
 * 
 * @@author ���r(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinChangeCommonRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinChangeCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_aio_cashin_change_common";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211341L;
        
    /**
     * (�����ʒm���׈ꗗ)<BR>
     */
    public WEB3AioCashinNoticeUnit2[] cashinNoticeList;
    
    
    /**
     * @@roseuid 4158EB640294
     */
    public WEB3AdminAioCashinChangeCommonRequest() 
    {
     
    }
    
    /**
     * �����ʒm���ׂ̌������`�F�b�N����B <BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N  <BR>
     *  �����ʒm����.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A  <BR>
     * �u���X�R�[�h�G���[�v�̗�O���X���[����B  <BR>
     *  ���X�R�[�h == null  <BR>
     *  ���X�R�[�h.length != 3  <BR>
     *  ���X�R�[�h != ���l  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h�`�F�b�N <BR>
     *  �����ʒm����.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A  <BR>
     * �u�ڋq�R�[�h�G���[�v�̗�O���X���[����B  <BR>
     *  �ڋq�R�[�h == null  <BR>
     *  �ڋq�R�[�h.length != 6  <BR>
     *  �ڋq�R�[�h != ���l  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * �R�j�����ʒm����.���l�̍ő啶�����`�F�b�N <BR>
     * �����ʒm����.���l��null�łȂ��Ƃ� <BR>
     * length��50�����ȉ��łȂ��ꍇ <BR>
     * �u���l�̃`�F�b�N�v��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00487<BR>
     * 
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�����ʒm���ׂ̌������`�F�b�N����B
        for (int i = 0; i < cashinNoticeList.length; i++)
        {
            
            WEB3AioCashinNoticeUnit2 l_cashinNotice
                = this.cashinNoticeList[i];
            
            //�P�j���X�R�[�h�`�F�b�N 
            // ���X�R�[�h == null 
            // ���X�R�[�h.length != 3  
            // ���X�R�[�h != ���l 
            if (WEB3StringTypeUtility.isEmpty(l_cashinNotice.branchCode) ||
                l_cashinNotice.branchCode.length() != 3 ||
                !WEB3StringTypeUtility.isNumber(l_cashinNotice.branchCode))
            {
                log.exiting(STR_METHOD_NAME);   
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ���N�G�X�g�f�[�^.�����ʒm����[" + i +"] "+                    
                    "���X�R�[�h == null or" +
                    "���N�G�X�g�f�[�^.���X�R�[�h.length() != 3 " +
                    "���X�R�[�h != ���l" );
            }      
            
            //�Q�j�ڋq�R�[�h�`�F�b�N 
            //�����ʒm����.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A  
            //�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B  
            // �ڋq�R�[�h == null  
            // �ڋq�R�[�h.length != 6  
            // �ڋq�R�[�h != ���l  

            if (WEB3StringTypeUtility.isEmpty(l_cashinNotice.accountCode) ||
                l_cashinNotice.accountCode.length() != 6 ||
                !WEB3StringTypeUtility.isNumber(l_cashinNotice.accountCode))       
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ���N�G�X�g�f�[�^.�����ʒm����[" + i +"] "+
                    " �ڋq�R�[�h == null  or " +
                    " �ڋq�R�[�h.length != 6 or " +
                    " �ڋq�R�[�h != ���l " );   
            }  
            
            // �R�j�����ʒm����.���l�̍ő啶�����`�F�b�N 
            // �����ʒm����.���l��null�łȂ��Ƃ� 
            //length��50�����ȉ��łȂ��ꍇ 
            //�u���l�̃`�F�b�N�v��O���X���[����B
            
            if (WEB3StringTypeUtility.isNotEmpty(l_cashinNotice.remark) &&
                    l_cashinNotice.remark.length() > 50 )       
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00487,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ���N�G�X�g�f�[�^.�����ʒm����[" + i +"] "+
                    "�����ʒm����.���l��null�łȂ��Ƃ� length ��50�����ȉ��łȂ�" 
                    );   
            }            
            
        }

    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return null;        
    }
}
@
