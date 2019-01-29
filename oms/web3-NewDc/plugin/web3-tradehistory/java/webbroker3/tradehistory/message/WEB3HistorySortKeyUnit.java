head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������\�[�g�L�[(WEB3HistorySortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 �͌d�� (���u) �V�K�쐬
*/
package webbroker3.tradehistory.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradehistory.define.WEB3HistoryKeyItemDef;


/**
 * (��������\�[�g�L�[)<BR>
 * ��������\�[�g�L�[�N���X<BR>
 *                                                                
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3HistorySortKeyUnit extends Message 
{

    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistorySortKeyUnit.class);    


    /**
     * (�L�[����)<BR>
     * �L�[���ځF�h��n���h�A�h�����h<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * �����^�~��<BR>
     * <BR>
     * A�F�@@����<BR>
     * D�F�@@�~��<BR>
     */
    public String ascDesc;
    
    /**
     * (��������\�[�g�L�[)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistorySortKeyUnit
     * @@roseuid 41340C40010D
     */
    public WEB3HistorySortKeyUnit() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00085              <BR>
     * <BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00087              <BR>
     * <BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00088         <BR>
     * <BR>
     * �S�jthis.�L�[���ڂ����L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�h��n���h<BR>
     * �@@�@@�@@�E�h�����h<BR>
     *      class         :  WEB3BusinessLayerException           <BR>
     *      tag            :  BUSINESS_ERROR_00086              <BR>
     * <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41340C3A00DE
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()"; 
        
        log.entering(STR_METHOD_NAME);  
        
        //�P�jthis.�L�[���ڂ̃`�F�b�N
        //     this.�L�[���ځ�null�̏ꍇ�A>
        //     �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if(this.keyItem == null || "".equals(this.keyItem))
        {
            log.error(" �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �Q�jthis.�����^�~���̃`�F�b�N
        //     this.�����^�~����null�̏ꍇ�A<BR>
        // �@@  �u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>         
        //
        if(this.ascDesc == null || "".equals(this.ascDesc))
        {
            log.error("�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �R�jthis.�����^�~���̃`�F�b�N
        //     this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
        // �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
        // �@@�@@�@@�E�hA�F�����h<BR>
        // �@@�@@�@@�E�hD�F�~���h<BR>         
        //
        if(!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.error("�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                            this.getClass().getName() + STR_METHOD_NAME);    
        }
        
        // �S�jthis.�L�[���ڂ̃`�F�b�N
        //     this.�L�[���ڂ����L�̍��ڈȊO�̏ꍇ�A<BR>
        // �@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
        // �@@�@@�@@�E�h��n���h<BR>
        // �@@�@@�@@�E�h�����h<BR>
        //
        if(!WEB3HistoryKeyItemDef.DELIVERY_DATE.equals(this.keyItem) && !WEB3HistoryKeyItemDef.EXEC_DATE.equals(this.keyItem))
        {
            log.error("�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                            this.getClass().getName() + STR_METHOD_NAME);    
        } 
         
        log.exiting(STR_METHOD_NAME);          
    }
}
@
