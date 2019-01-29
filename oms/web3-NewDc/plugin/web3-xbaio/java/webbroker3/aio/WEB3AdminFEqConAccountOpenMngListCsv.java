head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFEqConAccountOpenMngListCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��ꗗCSV(WEB3AdminFEqConAccountOpenMngListCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;

/**
 * (�O�������J�݊Ǘ��ꗗCSV)<BR>
 * �O�������J�݊Ǘ��ꗗCSV�N���X 
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */

public class WEB3AdminFEqConAccountOpenMngListCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �،���ЃR�[�h���x��
     */
    public String institutionCodeLabel = "�،���ЃR�[�h";
    
    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��
     */
    public String branchCodeLabel = "���X�R�[�h";
    
    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��
     */
    public String accountCodeLabel = "�ڋq�R�[�h";
    
    /**
     * (���ʃR�[�h���x��)<BR>
     * ���ʃR�[�h���x��
     */
    public String requestNumberLabel = "���ʃR�[�h";
    
    /**
     * (���O�i���j���x��)<BR>
     * ���O�i���j���x��
     */
    public String familyNameLabel = "���O�i���j";
    
    /**
     * (���O�i���j���x��)<BR>
     * ���O�i���j���x��
     */
    public String nameLabel = "���O�i���j";
    
    /**
     * (���[���A�h���X���x��)<BR>
     * ���[���A�h���X���x��
     */
    public String mailAddressLabel = "���[���A�h���X";
    
    /**
     * (�O�����������ԍ����x��)<BR>
     * �O�����������ԍ����x��
     */
    public String feqAccountCodeLabel = "�O�����������ԍ�";
    
    /**
     * (�����J�ݏ󋵋敪���x��)<BR>
     * �����J�ݏ󋵋敪���x��
     */
    public String accountOpenStatusDivLabel = "�����J�ݏ󋵋敪";
    
    /**
     * (����M�敪���x��)<BR>
     * ����M�敪���x��
     */
    public String sendRcvDivLabel = "����M�敪";
    
    /**
     * (��t���ʃR�[�h���x��)<BR>
     * ��t���ʃR�[�h���x��
     */
    public String resultCodeLabel = "��t���ʃR�[�h";
    
    /**
     * (�G���[���R�R�[�h���x��)<BR>
     * �G���[���R�R�[�h���x��
     */
    public String errorReasonCodeLabel = "�G���[���R�R�[�h";
    
    /**
     * (�X�V�҃R�[�h���x��)<BR>
     * �X�V�҃R�[�h���x��
     */
    public String lastUpdaterLabel = "�X�V�҃R�[�h";
    
    /**
     * (�쐬���t���x��)<BR>
     * �쐬���t���x��
     */
    public String createdTimestampLabel = "�쐬���t";
    
    /**
     * (�X�V���t���x��)<BR>
     * �X�V���t���x��
     */
    public String lastUpdatedTimestampLabel = "�X�V���t";
    
    /**
     * (�O�������J�݊Ǘ��ꗗCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B
     * @@roseuid 41F9DD3200F0
     */
    public WEB3AdminFEqConAccountOpenMngListCsv() 
    {
        super();
        
        this.createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR> 
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�،���ЃR�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.���ʃR�[�h���x�� <BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.���O�i���j���x�� <BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.���O�i���j���x�� <BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.���[���A�h���X���x�� <BR>
     * �@@�J�����ԍ��F 6 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 7 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�O�����������ԍ����x�� <BR>
     * �@@�J�����ԍ��F 7 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 8 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�����J�ݏ󋵋敪���x�� <BR>
     * �@@�J�����ԍ��F 8 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 9 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.����M�敪���x�� <BR>
     * �@@�J�����ԍ��F 9 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 10 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.��t���ʃR�[�h���x�� <BR>
     * �@@�J�����ԍ��F 10 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 11 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�G���[���R�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 11 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 12 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�X�V�҃R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 12 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 13 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�쐬���t���x�� �@@�J�����ԍ��F 13 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * �|�@@index = 14 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������J�݊Ǘ��ꗗCSV.�X�V���t���x�� <BR>
     * �@@�J�����ԍ��F 14 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * @@roseuid 41F9DC220024
     */
    private void createColumnHeader() 
    {
        List l_lisLabels = new Vector();
        
        l_lisLabels.add(this.institutionCodeLabel);
        l_lisLabels.add(this.branchCodeLabel);
        l_lisLabels.add(this.accountCodeLabel);
        l_lisLabels.add(this.requestNumberLabel);
        l_lisLabels.add(this.familyNameLabel);
        l_lisLabels.add(this.nameLabel);
        l_lisLabels.add(this.mailAddressLabel);
        l_lisLabels.add(this.feqAccountCodeLabel);
        l_lisLabels.add(this.accountOpenStatusDivLabel);
        l_lisLabels.add(this.sendRcvDivLabel);
        l_lisLabels.add(this.resultCodeLabel);
        l_lisLabels.add(this.errorReasonCodeLabel);
        l_lisLabels.add(this.lastUpdaterLabel);
        l_lisLabels.add(this.createdTimestampLabel);
        l_lisLabels.add(this.lastUpdatedTimestampLabel);
        
        //�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐���
        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel[l_lisLabels.size()];
        for (int i = 0; i < l_lisLabels.size(); i ++) 
        {
            //�J�����w�b�_�z��           
            if (l_lisLabels.get(i).equals(this.createdTimestampLabel) || 
                    l_lisLabels.get(i).equals(this.lastUpdatedTimestampLabel))
            {
                l_gentradeCsvColumnModel[i] = 
                    new WEB3GentradeCsvColumnModel(
                        (String)l_lisLabels.get(i),
                        i,
                        WEB3GentradeCsvColumnModel.STRINGTYPE,
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
                continue;
            }
            l_gentradeCsvColumnModel[i] = 
                new WEB3GentradeCsvColumnModel(
                    (String)l_lisLabels.get(i),
                    i,
                    WEB3GentradeCsvColumnModel.STRINGTYPE,
                    null);
        }
        //set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����
        this.setColumnHeader(l_gentradeCsvColumnModel);
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�،���ЃR�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF4D64031C
     */
    public void setInstitutionCode(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�،���ЃR�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.institutionCodeLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF4F9B0222
     */
    public void setBranchCode(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.���X�R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.branchCodeLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�ڋq�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF4F9C035A
     */
    public void setAccountCode(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�ڋq�R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.accountCodeLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.���ʃR�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF4F9E0270
     */
    public void setRequestNumber(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.���ʃR�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.requestNumberLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set���O�i���j)<BR>
     * ���O�i���j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.���O�i���j���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF5036036A
     */
    public void setFamilyName(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.���O�i���j���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.familyNameLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set���O�i���j)<BR>
     * ���O�i���j���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.���O�i���j���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF50E500DA
     */
    public void setName(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.���O�i���j���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.nameLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set���[���A�h���X)<BR>
     * ���[���A�h���X���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.���[���A�h���X���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF50F903C8
     */
    public void setMailAddress(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.���[���A�h���X���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.mailAddressLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�O�����������ԍ�)<BR>
     * �O�����������ԍ����Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�O�����������ԍ����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF5117000F
     */
    public void setFeqAccountCode(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�O�����������ԍ����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.feqAccountCodeLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�����J�ݏ󋵋敪)<BR>
     * �����J�ݏ󋵋敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�����J�ݏ󋵋敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF512D0251
     */
    public void setAccountOpenStatusDiv(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�����J�ݏ󋵋敪���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.accountOpenStatusDivLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set����M�敪)<BR>
     * ����M�敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.����M�敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF514D03D7
     */
    public void setSendRcvDiv(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.����M�敪���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.sendRcvDivLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.��t���ʃR�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF516602AE
     */
    public void setResultCode(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.��t���ʃR�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.resultCodeLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�G���[���R�R�[�h)<BR>
     * �G���[���R�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�G���[���R�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF518603A8
     */
    public void setErrorReasonCode(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�G���[���R�R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.errorReasonCodeLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�X�V�҃R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_strValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF519B0176
     */
    public void setLastUpdater(int l_intRowId, String l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�X�V�҃R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.lastUpdaterLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�쐬���t)<BR>
     * �쐬���t���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�쐬���t���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_datValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF51AE00DA
     */
    public void setCreatedTimestamp(int l_intRowId, Date l_strValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�쐬���t���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.createdTimestampLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_strValue);
    }
    
    /**
     * (set�X�V���t)<BR>
     * �X�V���t���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F this.�X�V���t���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F ����.�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�l
     * @@param l_intRowId - �s�ԍ�
     * 
     * @@param l_datValue - CSV�ɃZ�b�g����l
     * @@roseuid 41FF51AE0186
     */
    public void setLastUpdatedTimestamp(int l_intRowId, Date l_datValue) 
    {
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F this.�X�V���t���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(this.lastUpdatedTimestampLabel);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l 
        this.setValue(l_intRowId, l_gentradeCsvColumnModel, l_datValue);
    }
}
@
