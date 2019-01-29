head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N��\���N���X(WEB3MQMessageSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2004/11/11 �R�c�@@��i(FLJ) �v���p�e�B.���[�U�[�f�[�^��ǉ�
                 : 2005/03/07 �R�c�@@��i(FLJ) �f�o�b�N�p��toString()���\�b�h������
*/
package webbroker3.mqgateway;

/**
 * <p>
 * MQ�ɑ��M���郁�b�Z�[�W�̃X�y�b�N��\���N���X�B<br>
 * <br>
 * WEB3MQMessageGateway��MQ�ɑ��M���郁�b�Z�[�W�𐶐�����̂ɁA<br>
 * �K�v�Ȉȉ��̏���ێ�����B<br>
 * <ul>
 *   <li>��ЃR�[�h</li>
 *   <li>�f�[�^�R�[�h</li>
 *   <li>���[�U�[�f�[�^</li>
 * </ul>
 * </p>
 * 
 * @@see webbroker3.mqgateway.WEB3MQGatewayService#send(WEB3MQMessageSpec)
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3MQMessageSpec
{

    /**
     * ��ЃR�[�h
     */
    private final String institutionCode;

    /**
     * �f�[�^�R�[�h
     */
    private final String dataCode;
    
    /**
     * ���[�U�[�f�[�^
     */
    private final String userData;
    
    /**
     * �R���X�g���N�^
     * 
     * @@param institutionCode ��ЃR�[�h
     * @@param dataCode �f�[�^�R�[�h
     */
    public WEB3MQMessageSpec(
        String institutionCode,
        String dataCode)
    {
        this.institutionCode = institutionCode;
        this.dataCode = dataCode;
        this.userData = null;
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @@param institutionCode ��ЃR�[�h
     * @@param dataCode �f�[�^�R�[�h
     * @@param userData ���[�U�[�R�[�h
     */
    public WEB3MQMessageSpec(
        String institutionCode,
        String dataCode,
        String userData)
    {
        this.institutionCode = institutionCode;
        this.dataCode = dataCode;
        this.userData = userData;
    }

    /**
     * ��ЃR�[�h���擾����B
     * 
     * @@return ��ЃR�[�h
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * �f�[�^�R�[�h���擾����B
     * 
     * @@return �f�[�^�R�[�h
     */
    public String getDataCode()
    {
        return dataCode;
    }
    
    /**
     * ���[�U�[�f�[�^���擾����B
     * 
     * @@return ���[�U�[�f�[�^
     */
    public String getUserData()
    {
        return userData;
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{");
        l_sb.append("institutionCode=").append(getInstitutionCode());
        l_sb.append(",dataCode=").append(getDataCode());
        l_sb.append(",userData=").append(getUserData());
        l_sb.append("}");
        return l_sb.toString();
    }
    
}
@
