head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ��������N�G�X�g�N���X(WEB3AdminTPPaymentRequisitionManageSearchRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ���������Ǘ��������N�G�X�g
 */
public class WEB3AdminTPPaymentRequisitionManageSearchRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_payment_requisition_manage_List";
     
    
    /**
     * ��ЃR�[�h
     */
    public String institutionCode;
    
    /**
     * �ڋq����
     * 
     * 0:�����ڋq 1:�M�p�ڋq
     */
    public String customerAttribute;
    
    /**
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * ���҃R�[�h
     */
    public String traderCode;

    /**
     * �E�v
     */
    public String summary;

    /**
     * �E�v����
     */
    public String summaryDays;

    /**
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * �y�[�W���\���s��
     */
    public String pageSize;
    
    /**
     * @@roseuid 4412A4DB013E
     */
    public WEB3AdminTPPaymentRequisitionManageSearchRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
 �@@�@@* �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
     *�P�j���X�R�[�h�`�F�b�N
     * this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
     * �u���X�R�[�h�G���[�v�̗�O���X���[����B
     * �Ethis.���X�R�[�h == null
     * �Ethis.���X�R�[�h.length != 3
     * �Ethis.���X�R�[�h != ���l
     *
     * �Q�j�ڋq�w��̏ꍇ(�ڋq�R�[�h  != null�̂Ƃ�)
     * this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
     * �u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
     * �Ethis.�ڋq�R�[�h.length != 6
     * �Ethis.�ڋq�R�[�h != ���l
     *
     * �R�j���Ҏw��̏ꍇ(���҃R�[�h  != null�̂Ƃ�)
     * this.���҃R�[�h���ȉ��̏����ɊY������ꍇ�A
     * �u���҃R�[�h�G���[�v�̗�O���X���[����B
     * �Ethis.���҃R�[�h.length != 5

     * @@roseuid 4402A1720318
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String METHOD_NAME = "validate()";

 		//�P�j���X�R�[�h�`�F�b�N
 		if(branchCode != null)
 		{
 		    if(branchCode.length() != 3)
 		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);
	  		try
			{
	  			Integer.parseInt(branchCode);
			}
	  		catch(NumberFormatException ne)
			{
	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME, ne.getMessage());
			}
 		}

        //�Q�j�ڋq�w��̏ꍇ
 		//�ڋq�R�[�h�`�F�b�N
 		if(accountCode != null)
 		{
 		    if(accountCode.length() != 6)
 		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME);
	  		try
			{
	  			Integer.parseInt(accountCode);
			}
	  		catch(NumberFormatException ne)
			{
	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME, ne.getMessage());
			}
 		}

        //�R�j���Ҏw��̏ꍇ
 		//���҃R�[�h�`�F�b�N
 		if(traderCode != null)
 		{
 		    if(traderCode.length() != 5)
 		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01912, METHOD_NAME);
 		}

    }

	public WEB3GenResponse createResponse() {
		return new WEB3AdminTPPaymentRequisitionManageSearchResponse();
	}
}
@
