head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOrderRouteInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����o�H���(WEB3AdminOrderRouteInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.115
*/

package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * �����o�H���N���X<BR>
 * <BR>
 * WEB3AdminOrderRouteInfo<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminOrderRouteInfo extends Message
{
      
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminOrderRouteInfo.class);
    

	/**
	 * �ϊ��s��R�[�h<BR>
	 */
	public String convertMarketCode;

    /**
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * �t�����g�����V�X�e���敪<BR>
     */
    public String frontOrderSystemCode;
    
    /**
     * �����^�C�v<BR>
     */
    public String productType;
    
    /**
     * �����o�H�敪<BR>
     */
    public String submitOrderRouteDiv;
    
    /**
     * �ύX�㔭���o�H�敪<BR>
     */
    public String changedSubmitOrderRouteDiv;
    
	/**
	 * �ؑփX�e�[�^�X�B<BR>
	 * <BR>
	 * �O �F �������B<BR>
	 * �P �F �ؑ֒��B<BR>
	 * �Q �F �ؑ֍ρB<BR>
	 * �X �F �ؑ֕s�B<BR>
	 */
	public String changeStatus;
    
	/**
	 * �ؑ֋N���敪�B<BR>
	 * <BR>
	 * �O �F �����o�H�ؑցB<BR>
	 * �P �F �ʔԏƉ�v���ċN���B<BR>
	 * �Q �F �ʒm��s�����v���ċN���B<BR>
	 * �R �F �ʒm��s�v���ċN���B<BR>
	 * �S �F �ʒm�đ��v���i��t�n�j�ċN���B<BR>
	 * �T �F �ʒm�đ��v���i���n�j�ċN���B<BR>
	 * �U �F �S���������ċN���B<BR>
	 * �V �F �ؑ֏��������B<BR>
	 */
	public String changeStartDiv;
    
    
    /**
     * �����o�H���N���X�R���X�g���N�^<BR>
     * @@roseuid 42E8A90901DC
     */
    public WEB3AdminOrderRouteInfo() 
    {
     
    }
    
	/**
	 * ���N���X�̐��������`�F�b�N����B<BR>
	 * <BR>
	 * �P�j�@@�ϊ��s��R�[�h�`�F�b�N<BR>
	 * �@@this.�ϊ��s��R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
     * <BR> 
	 * �Q�j�@@�����^�C�v<BR>
	 * �@@this.�����^�C�v == null�̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * �R�j�@@�����o�H<BR>
	 * �@@this.�����o�H == null�̏ꍇ�A��O���X���[����B<BR>
	 * @@roseuid 42D211F10321
	 */
    public void validate() throws WEB3BaseException 
    {
  
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
  
		//�ϊ��s��R�[�h�`�F�b�N
		//�G���[�̏ꍇ�́A��O��throw����B
		if (this.convertMarketCode == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02209,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        //�s��R�[�h�`�F�b�N
        //�G���[�̏ꍇ�́A��O��throw����B
        else if (this.marketCode == null){
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
		//�����^�C�v�`�F�b�N
		//�G���[�̏ꍇ�́A��O��throw����B
		else if (this.productType == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01109,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
		//�����o�H�敪�`�F�b�N
		//�G���[�̏ꍇ�́A��O��throw����B
		else if (this.submitOrderRouteDiv == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02210,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		log.exiting(STR_METHOD_NAME);
    }
}
@
