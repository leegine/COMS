head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ꗗ���N�G�X�g(WEB3AdminMailInfoReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mailinfo.define.WEB3AdminMailInfoKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���[�����ꗗ���N�G�X�g)<BR>
 * ���[�����ꗗ���N�G�X�g�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoReferenceRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoReferenceRequest.class);    
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���[�����\�[�g�L�[)<BR>
     * �@@�E���[��ID�@@�E����ID<BR>
     */
    public WEB3AdminMailInfoSortKey[] sortKeys;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w�� <BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;    

    /**
     * @@roseuid 416DEAAB0271
     */
    public WEB3AdminMailInfoReferenceRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * ���[�����ꗗ���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return webbroker3.mailinfo.common.WEB3GenResponse
     * @@roseuid 413C0D6C02AF
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoReferenceResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X���p�\�[�g�L�[�̃`�F�b�N<BR>
     *  1-1) this.���[�����\�[�g�L�[��null�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00231                <BR>
     *     =============================================== <BR>
     *  1-2) this.���[�����\�[�g�L�[�̗v�f����0�̏ꍇ�A��O���X���[����B<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00232                <BR>
     *     =============================================== <BR>
     *  1-3) this.���[�����\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@1-3-1) this.���[�����\�[�g�L�[.�L�[���ڂ�null�̏ꍇ�A��O���X���[����B    
     * =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00085                <BR>
     *     =============================================== <BR>
     * <BR>
     * �@@1-3-2) this.���[�����\�[�g�L�[.�L�[���ڂ��ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@�@@"�v���O����ID"<BR>
     * �@@�@@�@@"����ID"<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00086                <BR>
     *     =============================================== <BR>
     * �@@1-3-2) this.���[�����\�[�g�L�[.�����^�~����null�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00087                <BR>
     *     =============================================== <BR>
     * �@@1-3-3) this.���[�����\�[�g�L�[.�����^�~�����ȉ�<BR>
     * �̒l�ȊO�������ꍇ�A��<Br>�O���X���[����B<BR>
     * �@@�@@�@@"A:����"<BR>
     * �@@�@@�@@"D:�~��"<BR>
     * <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00088                <BR>
     *     =============================================== <BR>
     * 2) �v���y�[�W�ԍ��`�F�b�N <BR>
     *  2-1) this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00089                <BR>
     *     =============================================== <BR>
     *  2-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00090                <BR>
     *     =============================================== <BR>
     * 3) �y�[�W���\���s���`�F�b�N <BR>
     *  3-1) this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00091                <BR>
     *     =============================================== <BR>
     *  3-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00092                <BR>
     *     =============================================== <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C0DF902AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //  1-1) this.���[�����\�[�g�L�[��null�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, this.getClass().getName() + STR_METHOD_NAME); 
        }
               
        //  1-2) this.���[�����\�[�g�L�[�̗v�f����0�̏ꍇ�A��O���X���[����.
        int l_intSortKeyCnt = this.sortKeys.length;      
        if (l_intSortKeyCnt == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //  1-3)this.���[�����\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B
        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
          // �@@1-3-1) this.���[�����\�[�g�L�[.�L�[���ڂ�null�̏ꍇ�A��O���X���[����B              
            if (sortKeys[i].keyItem == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00085, this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // �@@1-3-2) this.���[�����\�[�g�L�[.�L�[���ڂ��ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            if(!WEB3AdminMailInfoKeyItemDef.SENDMAIL_DIV.equals(sortKeys[i].keyItem)
                && !WEB3AdminMailInfoKeyItemDef.DISCERN_ID.equals(sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086, this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //�@@1-3-3) this.���[�����\�[�g�L�[.�����^�~����null�̏ꍇ ��O���X���[����B<BR>
            if (sortKeys[i].ascDesc == null )
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00087, this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // �@@1-3-4) this.���[�����\�[�g�L�[.�����^�~�����ȉ�<BR>
            // �̒l�ȊO�������ꍇ�A��<Br>�O���X���[����B<BR>
            // �@@�@@�@@"A:����"<BR>
            // �@@�@@�@@"D:�~��"<BR>
            
            if (!WEB3AscDescDef.ASC.equals(sortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(sortKeys[i].ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00088, this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        
        //  2-1) this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
              
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089, this.getClass().getName() + STR_METHOD_NAME);
        }
        //  2-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
                       
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex) )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, this.getClass().getName() + STR_METHOD_NAME);
        }
        //  3-1) this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B 
      
        
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, this.getClass().getName() + STR_METHOD_NAME); 
        }
        //  3-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        
        
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
