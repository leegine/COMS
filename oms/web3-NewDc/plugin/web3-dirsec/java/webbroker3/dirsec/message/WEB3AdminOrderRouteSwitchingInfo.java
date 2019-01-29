head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOrderRouteSwitchingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE��������(WEB3AdminOrderRouteSwitchingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * ��������N���X<BR>
 */
public class WEB3AdminOrderRouteSwitchingInfo extends WEB3AdminOrderRouteInfo
{
    
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminOrderRouteSwitchingInfo.class);
   
	/**
	 * �L���t���O<BR>
	 * <BR>
	 * false�F�@@����<BR>
	 * true�F�@@�L��<BR>
	 */
	public String validFlag;

	/**
	 * �ύX��L���t���O<BR>
	 * <BR>
	 * false�F�@@����<BR>
	 * true�F�@@�L��<BR>
	 */
	public String changedValidFlag;
    
	/**
	 * ��������N���X�R���X�g���N�^<BR>
	 * @@roseuid 42EEC5820000
	 */
	public WEB3AdminOrderRouteSwitchingInfo() 
	{
     
	}

    /**
     * ���N���X�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�L���t���O�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * �Q�j�@@�ύX��L���t���O�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR> 
     * @@roseuid 42D2123E0246
     */
    public void validate() throws WEB3BaseException 
    {
     
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
     
        // �L���t���O�`�F�b�N
        // �G���[�̏ꍇ�́A��O��throw����B
        if (this.validFlag == null){
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02214,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �ύX��L���t���O�`�F�b�N
        // �G���[�̏ꍇ�́A��O��throw����B
        else if (this.changedValidFlag == null){
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02215,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
