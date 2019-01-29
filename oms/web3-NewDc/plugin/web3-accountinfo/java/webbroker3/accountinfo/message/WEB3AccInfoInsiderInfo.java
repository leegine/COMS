head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoInsiderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ҏ�񃁃b�Z�[�W(WEB3AccInfoInsiderInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.accountinfo.define.WEB3RegStateDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����ҏ��)<BR>
 * �����ҏ�񃁃b�Z�[�W<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoInsiderInfo extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoInsiderInfo.class);
        
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�֌W�R�[�h)<BR>
     * �֌W�R�[�h<BR>
     * <BR>
     * 01�F����<BR>
     * 02�F�����z���<BR>
     * 03�F��������<BR>
     * 04�F��v����<BR>
     * 05�F�劔��<BR>
     * 06�F�֌W���<BR>
     * 07�F�����E��<BR>
     * 08�F�֌W�����z���<BR>
     * 09�F����Г��̐e��Ђ̖�E��<BR>
     * 10�F����Г��̎q��Ђ̖�E��<BR>
     * <BR>
     */
    public String relationCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String executive;
    
    /**
     * (��E��)<BR>
     * ��E��<BR>
     */
    public String position;
    
    /**
     * (�o�^�󋵋敪)<BR>
     * �o�^�󋵋敪<BR>
     * <BR>
     * 0�F�@@�`�F�b�N���Ȃ�<BR>
     * 1�F�@@�x���̂�<BR>
     * 2�F�@@������~<BR>
     * <BR>
     * �������҃}�X�^�[�e�[�u��.�o�^�󋵋敪<BR>
     * <BR>
     */
    public String registStateDiv;
    
    /**
     * @@roseuid 418F39F2035B
     */
    public WEB3AccInfoInsiderInfo() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�����R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �Q�j�@@�o�^�󋵋敪�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01137<BR>
     * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01138<BR>  
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415D045C02BC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
       /* 
        * �P�j�@@�����R�[�h�̃`�F�b�N<BR>
        * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00079<BR>
        */
        if(productCode == null || "".equals(productCode))
        {
            log.debug("[�����R�[�h] = " + productCode);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�����w��ł�");
        }
        
       /* 
        * �Q�j�@@�o�^�󋵋敪�̃`�F�b�N<BR>
        * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01137<BR>
        */
        if(registStateDiv == null || "".equals(registStateDiv))
        {
            log.debug("[�o�^�󋵋敪] = " + registStateDiv);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01137, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�o�^�󋵋敪������");
        }
        
       /* 
        * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01138<BR>  
        */
        if(!(WEB3RegStateDivDef.CHECK).equals(registStateDiv)       &&
            !(WEB3RegStateDivDef.STOP_ORDER).equals(registStateDiv) &&
            !(WEB3RegStateDivDef.WARNING).equals(registStateDiv))
        {
            log.debug("[�o�^�󋵋敪] = " + registStateDiv);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01138, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�o�^�󋵋敪�s���ȃR�[�h�l");
        }
    }
}
@
