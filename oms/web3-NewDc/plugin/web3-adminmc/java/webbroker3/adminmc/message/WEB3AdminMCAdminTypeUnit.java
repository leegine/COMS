head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminTypeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃^�C�v���(WEB3AdminMCAdminTypeUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22  �� �� �@@ (���u) �V�K�쐬
                 : 2006/08/28 �юu�� (���u) �d�l�ύX ���f��022
*/
package webbroker3.adminmc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.adminmc.define.WEB3AdminMCAdminTypeUnitDef;

/**
 * (�Ǘ��҃^�C�v���)<BR>
 * �Ǘ��҃^�C�v���<BR>
 * @@author �����@@
 * @@version 1.0
 */
 
public class WEB3AdminMCAdminTypeUnit extends Message 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminTypeUnit.class); 
    
    /**
     * (�������x���R�[�h)<BR>
     * �������x���R�[�h<BR>
     */
    public String permissionLevel;
    
    /**
     * (�������x������)<BR>
     * �������x������<BR>
     * <BR>
     * ���O���[�v����<BR>
     */
    public String permissionLevelName;
    
    /**
     * (DIR�Ǘ��҃t���O)<BR>
     * DIR�Ǘ��҃t���O <BR>
     * <BR>
     * �ʏ�Ǘ��ҁF0<BR> 
     * DIR�Ǘ��ҁF1 <BR>
     * �ʏ�Ǘ��ҁi�\���ҁj�F2<BR> 
     * �ʏ�Ǘ��ҁi���F�ҁj�F3 <BR>
     */
    public String dirAdminFlag;
    
    /**
     * (�S���X���t���O)<BR>
     * �S���X���t���O<BR>
     * <BR>
     * �S���X���̏ꍇtrue�C�ȊO�Afalse<BR>
     */
    public boolean allBranchPermissionFlag;
    
    /**
     * @@roseuid 419864230138
     */
    public WEB3AdminMCAdminTypeUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�������x���R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01201 <BR>
     * �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�i�������x���R�[�h.length != 3�j�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01202 <BR>
     * �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01203<BR>
     * <BR>
     * �Q�j�@@�������x�����̂̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01204 <BR>
     * �@@�Q�|�Q�j�@@��������100byte���傫���ꍇ�i�������x������.length > 100�j�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01205  <BR>
     * <BR>
     * �R�j�@@DIR�Ǘ��҃t���O�C�������x���R�[�h�̃`�F�b�N<BR>
     * �@@�R�|�P�j DIR�Ǘ��҂̏ꍇ�ithis.isDIR�Ǘ���() == true�j<BR>
     * �@@�@@�i�������x���R�[�h�̊J�n���� != '9'�j�̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :�@@BUSINESS_ERROR_01206  <BR>
     * �@@�R�|�Q�j �ʏ�Ǘ��҂̏ꍇ�ithis.isDIR�Ǘ���() == false�j <BR>
     * �@@�@@�i�������x���R�[�h�̊J�n���� == '9'�j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�� DIR�Ǘ��҂̌������x���R�[�h��'9'�Ŏn�܂镶����łȂ���΂Ȃ�Ȃ��B<BR>
     * �@@�@@�@@�܂��A�ʏ�Ǘ��҂̏ꍇ�́A'9'�Ŏn�܂錠�����x���R�[�h�͎g�p�ł��Ȃ��B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01207 <BR>
     * <BR>
     * <BR>
     * @@roseuid 41760FB50031
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
         // �P�j�@@�������x���R�[�h�̃`�F�b�N<BR>
         // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01201 <BR>
         if (this.permissionLevel == null)
         {
            log.error(" �������x���R�[�h�������� .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                            this.getClass().getName() + STR_METHOD_NAME);
         }
         
         // �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�i�������x���R�[�h.length != 3�j�A��O���X���[����B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01202 <BR>
         if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
         {
            log.error(" ��������3byte�łȂ��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                            this.getClass().getName() + STR_METHOD_NAME);
         }         
         
         // �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01203<BR>
         if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
         {
            log.error(" �����ȊO�̕������܂܂��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                            this.getClass().getName() + STR_METHOD_NAME);
         }        
        
         // �Q�j�@@�������x�����̂̃`�F�b�N<BR>
         // �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01204 <BR>
         if (this.permissionLevelName == null)
         {
            log.error(" �������x�����̂������� .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01204,
                            this.getClass().getName() + STR_METHOD_NAME);
         }         
         
         // �@@�Q�|�Q�j�@@��������100byte���傫���ꍇ�i�������x������.length > 100�j�A��O���X���[����B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag : BUSINESS_ERROR_01205  <BR>
         if (WEB3StringTypeUtility.getByteLength(this.permissionLevelName) > 100)
         {
            log.error(" ��������100byte���傫���ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01205,
                            this.getClass().getName() + STR_METHOD_NAME);
         }         
         
         
         // �R�j�@@DIR�Ǘ��҃t���O�C�������x���R�[�h�̃`�F�b�N<BR>
         // �@@�R�|�P�j DIR�Ǘ��҂̏ꍇ�ithis.isDIR�Ǘ���() == true�j<BR>
         // �@@�@@�i�������x���R�[�h�̊J�n���� != '9'�j�̏ꍇ�A��O���X���[����B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :�@@BUSINESS_ERROR_01206  <BR>
         if (this.isDIRAdmin() 
             && (this.permissionLevel.charAt(0) != WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME_FIRST))
         {
            log.error(" DIR�Ǘ��҂̏ꍇ, �������x���R�[�h�̊J�n���� != '9'.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01206,
                            this.getClass().getName() + STR_METHOD_NAME);
         }          
         
         // �@@�R�|�Q�j �ʏ�Ǘ��҂̏ꍇ�ithis.isDIR�Ǘ���() == false�j <BR>
         // �@@�@@�i�������x���R�[�h�̊J�n���� == '9'�j�̏ꍇ�A��O���X���[����B<BR>
         // �@@�� DIR�Ǘ��҂̌������x���R�[�h��'9'�Ŏn�܂镶����łȂ���΂Ȃ�Ȃ��B<BR>
         // �@@�@@�@@�܂��A�ʏ�Ǘ��҂̏ꍇ�́A'9'�Ŏn�܂錠�����x���R�[�h�͎g�p�ł��Ȃ��B<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag : BUSINESS_ERROR_01207 <BR>     
         if (!this.isDIRAdmin() 
             && (this.permissionLevel.charAt(0) == WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME_FIRST))
         {
            log.error(" �ʏ�Ǘ��҂̏ꍇ, �������x���R�[�h�̊J�n���� == '9'.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01207,
                            this.getClass().getName() + STR_METHOD_NAME);
         }          
         log.exiting(STR_METHOD_NAME);     
         
    }
    
    /**
     * (validateDIR�Ǘ���)<BR>
     * �c�h�q�Ǘ��҃`�F�b�N���s��<BR>
     * <BR>
     * �ʏ�Ǘ��ҁiisDIR�Ǘ��ҁi�I�y���[�^�j == false�j�̏ꍇ�A<BR>
     * �ithis.isDIR�Ǘ���() == true�j�ł���΁A��O���X���[����B<BR>
     * �� �ʏ�Ǘ��҂́ADIR�Ǘ��҂̊Ǘ��҃^�C�v���X�V�ł��Ȃ��B<BR>
     *        class :  WEB3BusinessLayerException <BR>
     *        tag :  BUSINESS_ERROR_01208             <BR>
     * <BR>
     * @@param isDIR�Ǘ��ҁi�I�y���[�^�j - isDIR�Ǘ��ҁi�I�y���[�^�j
     * 
     * �� ���O�C�����̊Ǘ��҂�DIR�Ǘ��҂ł��邩�̔���B
     * @@roseuid 4178C5DF014F
     */
    public void validateDIRAdmin(boolean l_blnIsDIRAdmin) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDIRAdmin()";

        log.entering(STR_METHOD_NAME);    	  
        // �ʏ�Ǘ��ҁiisDIR�Ǘ��ҁi�I�y���[�^�j == false�j�̏ꍇ�A<BR>
        // this.isDIR�Ǘ���() == true�j�ł���΁A��O���X���[����B<BR>
        // �� �ʏ�Ǘ��҂́ADIR�Ǘ��҂̊Ǘ��҃^�C�v���X�V�ł��Ȃ��B<BR>
        //        class :  WEB3BusinessLayerException <BR>
        //        tag :  BUSINESS_ERROR_01208             <BR>
         if (!(l_blnIsDIRAdmin) && (this.isDIRAdmin()))
         {
            log.error(" �ʏ�Ǘ��҂̏ꍇ ,this.isDIR�Ǘ���() == true");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01208,
                            this.getClass().getName() + STR_METHOD_NAME);
         }        
         log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (isDIR�Ǘ���)<BR>
     * DIR�Ǘ��҂��𔻒肷��B<BR> 
     * <BR>
     * �@@�|�ithis.DIR�Ǘ��҃t���O  = 1 �j�̏ꍇtrue��ԋp����B<BR> 
     * �@@�|�ithis.DIR�Ǘ��҃t���O != 1 �j�̏ꍇfalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isDIRAdmin()
    {
        final String STR_METHOD_NAME = " isDIRAdmin()";
        log.entering(STR_METHOD_NAME);
        
        //�ithis.DIR�Ǘ��҃t���O  = 1 �j�̏ꍇtrue��ԋp����B
        if (WEB3DirAdminFlagDef.DIR_ADMINISTRATOR.equals(this.dirAdminFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�ithis.DIR�Ǘ��҃t���O != 1 �j�̏ꍇfalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
