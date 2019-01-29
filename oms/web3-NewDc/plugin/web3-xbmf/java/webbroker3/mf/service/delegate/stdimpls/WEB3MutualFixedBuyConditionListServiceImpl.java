head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�����ꗗ�T�[�r�XImpl (WEB3MutualFixedBuyConditionListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 ���G�� (���u) �V�K�쐬
                   2006/08/10 �R���@@�iSRA�j�d�l�ύX No486
Revesion History : 2007/08/30 ��іQ (���u) �d�l�ύX No.569,572
Revesion History : 2008/07/08 ���u�� (���u) �d�l�ύX ���f��No.606,613
Revesion History : 2008/07/31 ���u�� (���u) �d�l�ύX ���f��No.621,624
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeDivDef;
import webbroker3.common.define.WEB3FinInstitutionDivDef;
import webbroker3.common.define.WEB3MFStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl;
import webbroker3.mf.WEB3MutualFixedBuyDrawAccount;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.message.WEB3MutualFixedBuyAccountInfo;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyTotalUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�莞��z���t�����ꗗ�T�[�r�XImpl )<BR>
 * ���M�莞��z���t�����ꗗ�T�[�r�X�����N���X <BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListServiceImpl 
    extends WEB3MutualClientRequestService
    implements WEB3MutualFixedBuyConditionListService
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionListServiceImpl.class);
    
    /**
     * ���M�莞��z���t�����ꗗ�T�[�r�X�������s���B<BR> 
     * <BR>
     * �V�[�P���X�}�Q��<BR>
     *  ========================================================<BR>
     * ���ݍ�p�}: ���M�莞��z���t�����ꗗ / ���M�莞��z���t�����ꗗ: <BR>
     * ��O(�莞��z���t�����{�G���[)���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02520<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3MutualFixedBuyConditionListRequest l_��utualFixedBuyConditionListRequest = null;
        if (l_request instanceof WEB3MutualFixedBuyConditionListRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u ���M�莞��z���t�����ꗗ���̓��N�G�X�g�v�̏ꍇ
            l_��utualFixedBuyConditionListRequest = (WEB3MutualFixedBuyConditionListRequest) l_request;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //get�⏕����( )
        //�⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();
        
        //validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();       

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        //get���M�����J�e�S���[���X�g(String)
        //[get���M�����J�e�S���[���X�g�ɓn������]  
        // �،���ЃR�[�h�F�擾�����⏕�����I�u�W�F�N�g.getInstitution().getInstitutionCode()
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        List l_lisProductCategory = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);    
        
        //get���M�����J�e�S���[���X�g()�̖߂�l>0���̏ꍇ
        WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits = null;
        if (l_lisProductCategory.size() > 0)
        {
            //create���M�����J�e�S���[�ꗗ(List)
            //���M�����J�e�S���[�ꗗ���쐬����B  
            //[create���M�����J�e�S���[�ꗗ�ɓn������]  
            //�@@�����J�e�S���[�ꗗ�Fget���M�����J�e�S���[���X�g()�̖߂�l
            l_mfProductCategoryUnits =
                l_mfProductManager.createMutualFundProductCategoryList(
                    l_lisProductCategory);            
        }
             
        //get�莞��z���t�������X�g(String, String, String, String, Object[])
        //�莞��z���t�����e�[�u�����烌�R�[�h���擾����B 
        //[get�莞��z���t�������X�g�̈���] 
        //(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String, �������������� : String, ���������l : Object[]) 
        //�،���ЃR�[�h   �F�@@�擾�����⏕�����I�u�W�F�N�g .getInstitution().getInstitutionCode()�̖߂�l 
        //���X�R�[�h�@@�@@�@@   �F�@@�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l 
        //�����R�[�h�@@�@@�@@   �F�@@�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l 
        //�������������� �F�@@null 
        //���������l�@@  �@@ �F�@@null   
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        List l_lisFixedBuyConditionList = l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode, 
            null, null);

        //<��������������쐬>
        //��������������Ƃ��āA�ȉ��̕������ݒ肷��B
        //"�����敪 in (?,?)�@@or ( �ύX�敪 in (?,?) and �폜�t���O = ? )"
        String l_strQueryString = " ( status in (?, ?) "
            + " or ( change_div in (?, ?) and delete_flag = ? )) ";

        //<���������l�ݒ�>
        //Object�z��Ɉȉ��̒l��ǉ�
        //1�Fsonar�����M
        //�Q�Fsonar���M��
        //3�F����
        //4�F�ꎞ��~
        //0:FALSE
        Object[] l_objQueryValues = new Object[]{
            WEB3MFStatusDef.SONAR_NOT_SEND,
            WEB3MFStatusDef.SONAR_SENDED,
            WEB3ChangeDivDef.RELEASE,
            WEB3ChangeDivDef.TEMP_STOP,
            BooleanEnum.FALSE
            };

        //get�莞��z���t�����ύX���X�g(
        //�،���ЃR�[�h : String,
        //���X�R�[�h : String,
        //�����R�[�h : String,
        //�������������� : String,
        //���������l : Object[])
        //�،���ЃR�[�h   �F�@@�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�@@�@@�@@   �F�@@�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        //�����R�[�h�@@�@@�@@   �F�@@�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l
        //�������������� �F�@@�쐬������������������
        //���������l�@@  �@@ �F�@@�쐬�������������l�ݒ�
        List l_lisFixedBuyConditionChangeList =
            l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strQueryString,
                l_objQueryValues);

        //calc�K�p�J�n�N���i�Ɩ����t�j
        //�K�p�J�n�N���i�Ɩ����t�j���擾����B
        //[����]
        //�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�@@�@@�@@�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l
        Date l_datOrderBizdate =
            l_mutualFixedBuyCommonServiceImpl.calcValidStartDateOrderBizdate(
                l_strInstitutionCode,
                l_strBranchCode);

        //���P�����ڕ\�����X�g�擾��
        //get�w��N���莞��z���t�������X�g(List, Date)
        //�P�����ڂ̒莞��z���t�������X�g���擾����B
        //[����]
        //�莞��z���t�������X�g�Fget�莞��z���t�������X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�Ŏ擾�����N���|�P����
        List l_lisFirstSelectMYLists = this.getSelectMYFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            WEB3DateUtility.addMonth(l_datOrderBizdate, -1));

        //get�w��N���莞��z���t�����ύX���X�g(List, Date)
        //�P�����ڂ̒莞��z���t�����ύX���X�g���擾����B
        //[����]
        //�莞��z���t�����ύX���X�g�Fget�莞��z���t�����ύX���X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�Ŏ擾�����N���|�P����
        List l_lisFirstSelectMYChangeLists =
            this.getSelectMYFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                WEB3DateUtility.addMonth(l_datOrderBizdate, -1));

        //��2�����ڕ\�����X�g�擾��
        //get�w��N���莞��z���t�������X�g(List, Date)
        //�Q�����ڂ̒莞��z���t�������X�g���擾����B
        //[����]
        //�莞��z���t�������X�g�Fget�莞��z���t�������X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�Ŏ擾�����N��
        List l_lisSecondSelectMYLists = this.getSelectMYFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            l_datOrderBizdate);

        //get�w��N���莞��z���t�����ύX���X�g(List, Date)
        //�Q�����ڂ̒莞��z���t�����ύX���X�g���擾����B
        //[����]
        //�莞��z���t�����ύX���X�g�Fget�莞��z���t�����ύX���X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�Ŏ擾�����N��
        List l_lisSecondSelectMYChangeLists =
            this.getSelectMYFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                l_datOrderBizdate);

        //��3�����ڕ\�����X�g�擾��
        //get�w��N���莞��z���t�������X�g(List, Date)
        //�R�����ڂ̒莞��z���t�������X�g���擾����B
        //[����]
        //�莞��z���t�������X�g�Fget�莞��z���t�������X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�Ŏ擾�����N��+�P����
        List l_lisThirdSelectMYLists = this.getSelectMYFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            WEB3DateUtility.addMonth(l_datOrderBizdate, 1));

        //get�w��N���莞��z���t�����ύX���X�g(List, Date)
        //�R�����ڂ̒莞��z���t�����ύX���X�g���擾����B
        //[����]
        //�莞��z���t�����ύX���X�g�Fget�莞��z���t�����ύX���X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�Ŏ擾�����N��+�P����
        List l_lisThirdSelectMYChangeLists =
            this.getSelectMYFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                WEB3DateUtility.addMonth(l_datOrderBizdate, 1));

        //���������\�����X�g�擾��
        //get�������莞��z���t�������X�g(List, Date)
        //�������̒莞��z���t�������X�g���擾����B
        //[����]
        //�莞��z���t�������X�g�Fget�莞��z���t�������X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�̖߂�l
        List l_lisFutureLists = this.getFutureFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            l_datOrderBizdate);

        //get�������莞��z���t�����ύX���X�g(List, Date)
        //�������̒莞��z���t�����ύX���X�g���擾����B
        //[����]
        //�莞��z���t�����ύX���X�g�Fget�莞��z���t�����ύX���X�g()�̖߂�l
        //�w��N���Fcalc�K�p�J�n�N���i�Ɩ����t�j�̖߂�l
        List l_lisFutureChangeLists =
            this.getFutureFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                l_datOrderBizdate);

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsFirst = null;
        Institution l_institution = l_subAccount.getInstitution();
        //��1�����ڕ\�����X�g�擾����get�w��N���莞��z���t�������X�g !=0 �܂���
        //��1�����ڕ\�����X�g�擾����get�w��N���莞��z���t�����ύX���X�g !=0  �̏ꍇ
        if (l_lisFirstSelectMYLists.size() != 0
            || l_lisFirstSelectMYChangeLists.size() != 0)
        {
            //merge�莞��z���t�����i�����\���j(Institution, List, List)
            //�P�����ڂ̒莞��z���t�������X�g��
            //�P�����ڂ̒莞��z���t�����ύX���X�g���}�[�W����
            //���M�莞��z���t�����s�̔z����쐬����B
            //[����]
            //�،����   �F�@@�擾�����⏕����.getInstitution()�̖߂�l
            //�莞��z���t�������X�g�@@�F
            //���P�����ڕ\�����X�g�擾��get�w��N���莞��z���t�������X�g()�̖߂�l
            //�莞��z���t�����ύX���X�g�@@�F
            //���P�����ڕ\�����X�g�擾��get�w��N���莞��z���t�����ύX���X�g()�̖߂�l
            l_mutualFixedBuyConditionUnitsFirst =
                this.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFirstSelectMYLists,
                    l_lisFirstSelectMYChangeLists);
        }

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsSecond = null;
        //���Q�����ڕ\�����X�g�擾����get�w��N���莞��z���t�������X�g !=0 �܂���
        //���Q�����ڕ\�����X�g�擾����get�w��N���莞��z���t�����ύX���X�g !=0  �̏ꍇ
        if (l_lisSecondSelectMYLists.size() != 0
            || l_lisSecondSelectMYChangeLists.size() != 0)
        {
            //merge�莞��z���t�����i�����\���j(Institution, List, List)
            //�Q�����ڂ̒莞��z���t�������X�g��
            //�Q�����ڂ̒莞��z���t�����ύX���X�g���}�[�W����
            //���M�莞��z���t�����s�̔z����쐬����B
            //[����]
            //�،����   �F�@@�擾�����⏕����.getInstitution()�̖߂�l
            //�莞��z���t�������X�g�@@�F
            //���Q�����ڕ\�����X�g�擾��get�w��N���莞��z���t�������X�g()�̖߂�l
            //�莞��z���t�����ύX���X�g�@@�F
            //���Q�����ڕ\�����X�g�擾��get�w��N���莞��z���t�����ύX���X�g()�̖߂�l
            l_mutualFixedBuyConditionUnitsSecond =
                this.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisSecondSelectMYLists,
                    l_lisSecondSelectMYChangeLists);
        }

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsThird = null;
        //��3�����ڕ\�����X�g�擾����get�w��N���莞��z���t�������X�g == 0 ����
        //��3�����ڕ\�����X�g�擾����get�w��N���莞��z���t�����ύX���X�g !=0  �̏ꍇ
        if (l_lisThirdSelectMYLists.size() == 0
            && l_lisThirdSelectMYChangeLists.size() != 0)
        {
            //merge�莞��z���t�����i�R�����\���j
            //�Q�����ڂ̒莞��z���t�������X�g��
            //�R�����ڂ̒莞��z���t�����ύX���X�g���}�[�W����
            //���M�莞��z���t�����s�̔z����쐬����B
            //[����]
            //�،����   �F�@@�擾�����⏕����.getInstitution()�̖߂�l
            //�莞��z���t�����s�@@�F�@@�Q�����ڕ\���f�[�^
            //�莞��z���t�����ύX���X�g�i�R�����ځj�@@�F
            //���R�����ڕ\�����X�g�擾��get�w��N���莞��z���t�����ύX���X�g()�̖߂�l
            l_mutualFixedBuyConditionUnitsThird =
                this.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_mutualFixedBuyConditionUnitsSecond,
                    l_lisThirdSelectMYChangeLists);
        }

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsFuture = null;
        //���������\�����X�g�擾����get�������莞��z���t�������X�g !=0  �̏ꍇ
        if (l_lisFutureLists.size() != 0)
        {
            //merge�莞��z���t�����i�����\���j(Institution, List, List)
            //�������̒莞��z���t�������X�g��
            //�������̒莞��z���t�����ύX���X�g���}�[�W����
            //���M�莞��z���t�����s�̔z����쐬����B
            //[����]
            //�،����   �F�@@�擾�����⏕����.getInstitution()�̖߂�l
            //�莞��z���t�������X�g�@@�F
            //���������\�����X�g�擾����get�������莞��z���t�������X�g()�̖߂�l
            //�莞��z���t�����ύX���X�g�@@�F
            //���������\�����X�g�擾����get�������莞��z���t�����ύX���X�g()�̖߂�l
            l_mutualFixedBuyConditionUnitsFuture =
                this.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFutureLists,
                    l_lisFutureChangeLists);
        }

        int l_intFirstLength =0;
        int l_intSecondLength = 0;
        int l_intThirdLength = 0;
        int l_intFutureLength = 0;
        
        //�擾�����\���f�[�^���瓊�M�莞��z���t�����s�̔z����쐬
        if (l_mutualFixedBuyConditionUnitsFirst != null)
        {
            l_intFirstLength = l_mutualFixedBuyConditionUnitsFirst.length;
        }
        if (l_mutualFixedBuyConditionUnitsSecond != null)
        {
            l_intSecondLength = l_mutualFixedBuyConditionUnitsSecond.length;
        }
        if (l_mutualFixedBuyConditionUnitsThird != null)
        {
            l_intThirdLength = l_mutualFixedBuyConditionUnitsThird.length;
        }
        if (l_mutualFixedBuyConditionUnitsFuture != null)
        {
            l_intFutureLength = l_mutualFixedBuyConditionUnitsFuture.length;
        }

        int l_intTotalLength = l_intFirstLength
            + l_intSecondLength
            + l_intThirdLength
            + l_intFutureLength;
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_intTotalLength];

        //�P�����ڕ\���f�[�^ != 0���̏ꍇ�A���M�莞��z���t�����s�̔z��ɒǉ�����B
        if (l_intFirstLength != 0)
        {
            for (int i = 0; i < l_intFirstLength; i++)
            {
                l_mutualFixedBuyConditionUnits[i] =
                    l_mutualFixedBuyConditionUnitsFirst[i];
            }
        }

        //�Q�����ڕ\���f�[�^ != 0���̏ꍇ�A���M�莞��z���t�����s�̔z��ɒǉ�����B
        if (l_intSecondLength != 0)
        {
            for (int i = 0; i < l_intSecondLength; i++)
            {
                l_mutualFixedBuyConditionUnits[l_intFirstLength + i] =
                    l_mutualFixedBuyConditionUnitsSecond[i];
            }
        }

        //�R�����ڕ\���f�[�^ != 0���̏ꍇ�A���M�莞��z���t�����s�̔z��ɒǉ�����B
        if (l_intThirdLength != 0)
        {
            for (int i = 0; i < l_intThirdLength; i++)
            {
                l_mutualFixedBuyConditionUnits[l_intFirstLength + l_intSecondLength + i] =
                    l_mutualFixedBuyConditionUnitsThird[i];
            }
        }

        //�������\���f�[�^ != 0���̏ꍇ�A���M�莞��z���t�����s�̔z��ɒǉ�����B
        if (l_intFutureLength != 0)
        {
            for (int i = 0; i < l_intFutureLength; i++)
            {
                l_mutualFixedBuyConditionUnits[l_intFirstLength + l_intSecondLength + l_intThirdLength + i] =
                    l_mutualFixedBuyConditionUnitsFuture[i];
            }
        }

        WEB3MutualFixedBuyTotalUnit[] l_mfBuyTotalUnits = null;
        WEB3MutualFixedBuyConditionUnit[] l_sortConditionUnits = null;
        //�����򏈗����쐬�������M�莞��z���t�����s�̔z�� != 0�� �̏ꍇ
        if (l_intTotalLength != 0)
        {
            //sort�莞��z���t�����ꗗ(���M�莞��z���t�����s[] )
            //�\�[�g����
            //[sort���M�莞��z���t����()�ɓn������]
            //���M�莞��z���t�����ꗗ�F�쐬�������M�莞��z���t�����s�̔z��
            l_sortConditionUnits =
                l_mutualFixedBuyCommonServiceImpl.sortFixedBuyConditionList(
                    l_mutualFixedBuyConditionUnits);

            //get�莞��z���t���z���v(���M�莞��z���t�����s[] )
            //�莞��z���t���t���z�̍��v���擾����B
            //[get�莞��z���t���t���z���v�̈���]
            //���M�莞��z���t�����s[ ] �F sort�莞��z���t�����ꗗ()�̖߂�l
            l_mfBuyTotalUnits = this.getFixedBuyTotalUnit(l_sortConditionUnits);
        }

        //create���X�|���X( )(���M�莞��z���t�����ꗗ���N�G�X�g::create���X�|���X)
        WEB3MutualFixedBuyConditionListResponse l_response = 
            (WEB3MutualFixedBuyConditionListResponse)l_��utualFixedBuyConditionListRequest.createResponse();
        
        //�莞��z���t��������(String, String, String)
        //�莞��z���t���������N���X�𐶐�����B 
        //[�莞��z���t���������ɓn������]  
        //�@@�،���ЃR�[�h�F�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //  ���X�R�[�h�F�擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l 
        //  �����R�[�h�F�擾�����⏕����.getMainAccount().getAccountCode()�̖߂�l
        WEB3MutualFixedBuyDrawAccount l_mfBuyDrawAccount = null;
        try
        {
            l_mfBuyDrawAccount = 
                new WEB3MutualFixedBuyDrawAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
        }
        catch (WEB3BaseException l_be)
        {
            log.debug("�莞��z���t���������ɊY������f�[�^������܂���!");           
        }
        //�����򏈗����莞��z���t���������Dget���Z�@@�֋敪=��s�̏ꍇ
        String[] l_strFinBranchNames = null;
        WEB3MutualFixedBuyAccountInfo l_mfBuyAccountInfo = null;
        if (l_mfBuyDrawAccount != null)
        {
            if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //get��s�x�X��(String, String)
                //[get��s�x�X���ɓn������] 
                // ��s�R�[�h�F�莞��z���t���������D��s�R�[�h 
                // �x�X�R�[�h�F�莞��z���t���������D�x�X�R�[�h    
                l_strFinBranchNames = l_mutualFixedBuyCommonServiceImpl.getFinBranchName(
                    l_mfBuyDrawAccount.getFinInstitutionCode(), 
                    l_mfBuyDrawAccount.getFinBranchCode());
            }
            
            //���M�莞��z�����������( )(���M�莞��z�����������::���M�莞��z�����������)
            //�莞��z�����������I�u�W�F�N�g�𐶐�����B
            l_mfBuyAccountInfo = new WEB3MutualFixedBuyAccountInfo();
              
            //��(*)�v���p�e�B�Z�b�g�� �E�E�E�i�`�j
            //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
            //�E��s�R�[�h                     = �莞��z���t���������D��s�R�[�h
            l_mfBuyAccountInfo.financialInstitutionCode = l_mfBuyDrawAccount.getFinInstitutionCode();
                
            //�E�x�X�R�[�h                     = �莞��z���t���������D�x�X�R�[�h
            l_mfBuyAccountInfo.financialBranchCode = l_mfBuyDrawAccount.getFinBranchCode();
                
            //�E���Z�@@�֋敪               = �莞��z���t���������D���Z�@@�֋敪
            l_mfBuyAccountInfo.financialInstitutionDiv = l_mfBuyDrawAccount.getFinInstitutionDiv();    
            
            //�E���������ԍ��@@�@@�@@�@@�@@�@@ = �莞��z���t���������D���������ԍ�
            l_mfBuyAccountInfo.financialAccountCode = l_mfBuyDrawAccount.getDrawAccountNo();
                
            //�E�����������`�l�i�J�i�j�@@�@@= �莞��z���t���������D�����������`�l�i�J�i�j
            l_mfBuyAccountInfo.financialAccountName = l_mfBuyDrawAccount.getDrawAccountNameKana();
                
            //���Z�@@�֋敪����s�̏ꍇ
            if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //�@@�E��s���@@�@@                    = get��s�x�X���̖߂�lString[0]
                l_mfBuyAccountInfo.financialInstitutionName = l_strFinBranchNames[0];
                
                //�@@�E�x�X��                         = get��s�x�X���̖߂�lString[1]
                l_mfBuyAccountInfo.financialBranchName = l_strFinBranchNames[1];
                
                //�@@�E�a���敪                      = �莞��z���t���������D�a���敪
                l_mfBuyAccountInfo.financialAccountDiv = l_mfBuyDrawAccount.getDepositDiv();
                
            }
            //���Z�@@�֋敪���X�֋ǂ̏ꍇ
            else if(WEB3FinInstitutionDivDef.POST_OFFICE.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //�@@�E��s���@@�@@                    = null
                l_mfBuyAccountInfo.financialInstitutionName = null;
                
                //�@@�E�x�X��                         = null
                l_mfBuyAccountInfo.financialBranchName = null;
                
                //�@@�E�a���敪                      = null
                l_mfBuyAccountInfo.financialAccountDiv = null;        
            }
        }
        //�����X�|���X�f�[�^�Z�b�g��
        //�ȉ��̒ʂ�A���X�|���X�f�[�^���Z�b�g����B
        
        //�E���M�����J�e�S���[�R�[�h�ꗗ   = create���M�����J�e�S���[�ꗗ()�̖߂�l
        l_response.categoryList = l_mfProductCategoryUnits;

        //�E���M�莞��z���t�����ꗗ   = �i�ȉ��̂Ƃ���j
        //merge�莞��z���t����()�̖߂�l != 0�� �̏ꍇ�Asort�莞��z���t�����ꗗ()�̖߂�l
        //merge�莞��z���t����()�̖߂�l == 0�� �̏ꍇ�Anull
        l_response.conditionList = l_sortConditionUnits;

        //�E���M�莞��z���t���z���v   = get�莞��z���t���t���z���v�̖߂�l                
        l_response.totalList = l_mfBuyTotalUnits;

        //�E���M�莞��z�����������   =�@@�i�ȉ��̂Ƃ���j
        //�莞��z���t�����������擾�ł����ꍇ�A�i�`�j�Ńv���p�e�B���Z�b�g�������M�莞��z�����������
        //�莞��z���t�����������擾�ł��Ȃ������ꍇ�Anull
        l_response.acountInfo = l_mfBuyAccountInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    

    /**
     * (merge�莞��z���t�����i�����\���j)<BR>
     * �莞��z���t�������X�g�̓��e�ƒ莞��z���t�����ύX���X�g�̓��e���}�[�W����<BR>
     * ���M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * <BR>
     * 1)�@@�莞��z���t���ʃT�[�r�X���擾����B<BR>
     * <BR>
     * 2)�@@����.�莞��z���t�������X�g�̌��������[�v���A���M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g���쐬����B<BR>
     * <BR>
     * �@@2)-1)�@@���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@2)-2)�@@�g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h<BR>
     * <BR>
     * �@@2)-3)�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@**** �莞��z���t�����e�[�u���̓��e�𓊐M�莞��z���t�����s�̃v���p�e�B�փZ�b�g����B<BR>
     * �@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�������F�擾�������M�����}�X�^Row.get������<BR>
     * �@@�@@�@@�@@�@@�@@���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���t���z�i���X�j�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�����\�������F�擾�������M�����}�X�^Row.get�\������<BR>
     * �@@�@@�@@�@@�@@�@@�K�p�J�n�N���F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�X�V�����Fnull<BR>
     * �@@�@@�@@�@@�@@�@@���������N���F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N��<BR>
     * �@@�@@�@@�@@�@@�@@�m��������z�i�ςݑ����j�F�莞��z���t���ʃT�[�r�X�Dcalc�ܗ^�m��������z(*1)�̖߂�l���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�ژ_�����{���`�F�b�N�Fnull<BR>
     * �@@�@@�@@�@@�@@�@@�ꎞ��~���t���O�Ffalse<BR>
     * <BR>
     * �@@2)-4)�@@���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * 3)�@@����.�莞��z���t�����ύX���X�g�Ń��[�v���A����.�莞��z���t�����ύX���X�g�̓��e��<BR>
     * �@@�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g�ɔ��f����B<BR>
     * <BR>
     * �@@3)-1)�@@���M�莞��z���t�����s�̃��X�g�Ń��[�v����B<BR>
     * <BR>
     * �@@�@@�@@3)-1)-1)�@@�ȉ��̏����Ŕ�r���āA��v����ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h   ==<BR>
     * �@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-1)-1)-1)�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������́A�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@3)-1)-1)-1)-1)�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�œ��M�莞��z���t�����s���㏑������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A�@@0<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A�@@0<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n�N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���������N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�m��������z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ꎞ��~���t���O�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A�@@false<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A�@@true<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-1)-1)-2)�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j== null�x �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z���t�����s�̃��X�g����폜����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@3)-1)-1)-2)-1)�@@���M�莞��z���t�����s�̃��X�g����Y������s�I�u�W�F�N�g���폜����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-1)-1)-3)�@@����.�莞��z���t�����ύX���X�g�̃��[�v�֖߂�B<BR>
     * <BR>
     * �@@3)-2)�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ<BR>
     * �@@�@@�@@�@@�������́A�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@3)-2)-1)�@@���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@3)-2)-2)�@@�g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@3)-2)-3)�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@**** ���M�莞��z���t�����ύX�e�[�u���݂̂ɑ��݂���ꍇ�A<BR>
     * �@@�@@�@@�@@**** ���M�莞��z���t�����ύX�e�[�u���̓��e�𓊐M�莞��z���t�����s�̃v���p�e�B�փZ�b�g����B<BR>
     * �@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�������F�擾�������M�����}�X�^Row.get������<BR>
     * �@@�@@�@@�@@�@@�@@���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A�@@0<BR>
     * �@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A�@@0<BR>
     * �@@�@@�@@�@@�@@�@@�����\�������F�擾�������M�����}�X�^Row.get�\������<BR>
     * �@@�@@�@@�@@�@@�@@�K�p�J�n�N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�X�V�����Fnull<BR>
     * �@@�@@�@@�@@�@@�@@���������N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�m��������z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�ژ_�����{���`�F�b�N�Fnull<BR>
     * �@@�@@�@@�@@�@@�@@�ꎞ��~���t���O�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�@@false��ݒ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�@@true��ݒ�<BR>
     * <BR>
     * �@@�@@�@@3)-2)-4)�@@�@@���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * <BR>
     * 4)�@@���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * 5)�@@���M�莞��z���t�����s�̔z������^�[������B<BR>
     * <BR>
     * (*1)�@@[calc�ܗ^�m��������z()�̈���]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�莞��z���t����Row�F�����D�莞��z���t�������X�g.�莞��z���t����Row<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_lisMutualFixedBuyConditionLists - (�莞��z���t�������X�g)<BR>
     * �莞��z���t�������X�g<BR>
     * @@param l_lisMutualFixedBuyConditionChangeLists - (�莞��z���t�����ύX���X�g)<BR>
     * �莞��z���t�����ύX���X�g<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionMonth(
        Institution l_institution,
        List l_lisMutualFixedBuyConditionLists,
        List l_lisMutualFixedBuyConditionChangeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionMonth("
            + "Institution, List, List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisMutualFixedBuyConditionLists == null
            || l_lisMutualFixedBuyConditionChangeLists == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp =
            (FinApp)Services.getService(FinApp.class);

        //�g�����M�����}�l�[�W�����擾����B
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //1) �莞��z���t���ʃT�[�r�X���擾����B
        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        //2) ����.�莞��z���t�������X�g�̌��������[�v���A���M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g���쐬����B
        List l_lisMutualFixedBuyConditionUnitLists = new ArrayList();
        Iterator l_iteratorMutualFixedBuyConditionLists =
            l_lisMutualFixedBuyConditionLists.iterator();
        while (l_iteratorMutualFixedBuyConditionLists.hasNext())
        {
            //2)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();

            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorMutualFixedBuyConditionLists.next();

            //2)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
            //�mget���M�����̈����n
            //�،���ЁF����.�،����
            //�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h
            MutualFundProduct l_mutualFundProduct;
            try {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_mfFixedBuyingCondRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂��� ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //2)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
            //�莞��z���t�����e�[�u���̓��e�𓊐M�莞��z���t�����s�̃v���p�e�B�փZ�b�g����B
            //[�Z�b�g������e]
            //�����R�[�h�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�����R�[�h
            l_mutualFixedBuyConditionUnit.mutualProductCode =
                l_mfFixedBuyingCondRow.getProductCode();
            //�������F�擾�������M�����}�X�^Row.get������
            l_mutualFixedBuyConditionUnit.mutualProductName =
                l_mutualFundProductRow.getStandardName();
            //���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h
            l_mutualFixedBuyConditionUnit.categoryCode =
                l_mutualFundProductRow.getCategoryCode();
            //���t���z�i���X�j�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i���X�j
            if (l_mfFixedBuyingCondRow.getMonthlyBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getMonthlyBuyAmount());
            }
            //���t���z�i�ςݑ����j�F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i�ςݑ����j
            if (l_mfFixedBuyingCondRow.getIncreaseBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getIncreaseBuyAmount());
            }
            //�����\�������F�擾�������M�����}�X�^Row.get�\������
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnit.displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.displayOrder =
                    l_mutualFundProductRow.getIndicationRanking() + "";
            }
            //�K�p�J�n�N���F�����D�莞��z���t�������X�g.�莞��z���t����Row�D�K�p�J�n�N��
            l_mutualFixedBuyConditionUnit.validStartDate =
                l_mfFixedBuyingCondRow.getValidStartDate();
            //�X�V�����Fnull
            l_mutualFixedBuyConditionUnit.updateDate = null;
            //���������N���F�����D�莞��z���t�������X�g.�莞��z���t����Row�D���������N��
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                new Timestamp(l_mfFixedBuyingCondRow.getDrawDate().getTime());
            //�m��������z�i�ςݑ����j�F
            //�莞��z���t���ʃT�[�r�X�Dcalc�ܗ^�m��������z(*1)�̖߂�l != 
            //�����D�莞��z���t�������X�g.�莞��z���t����Row�D���t���z�i�ςݑ����j�̏ꍇ�A
            //calc�ܗ^�m��������z�̖߂�l���Z�b�g
            //(*1) [calc�ܗ^�m��������z()�̈���]
            //�莞��z���t����Row�F�����D�莞��z���t�������X�g.�莞��z���t����Row
            String l_strPrizeAndDecisioDrawAmount = 
                l_mutualFixedBuyCommonService.calcPrizeAndDecisioDrawAmount(l_mfFixedBuyingCondRow);
            if(l_strPrizeAndDecisioDrawAmount == null)
            {
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
            }
            else if(!l_strPrizeAndDecisioDrawAmount.equals(WEB3StringTypeUtility.formatNumber(
                l_mfFixedBuyingCondRow.getIncreaseBuyAmount())))
            {
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = l_strPrizeAndDecisioDrawAmount;
            }
            //����ȊO�̏ꍇ�Anull
            else
            {
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
            }  
            //�ژ_�����{���`�F�b�N�Fnull
            l_mutualFixedBuyConditionUnit.checkResult = null;
            //�ꎞ��~���t���O�Ffalse
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;

            //2)-4) ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
            l_lisMutualFixedBuyConditionUnitLists.add(
                l_mutualFixedBuyConditionUnit);
        }

        //3) ����.�莞��z���t�����ύX���X�g�Ń��[�v���A����.�莞��z���t�����ύX���X�g�̓��e��
        //���M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g�ɔ��f����B
        Iterator l_iteratorMutualFixedBuyConditionChangeLists =
            l_lisMutualFixedBuyConditionChangeLists.iterator();
        while (l_iteratorMutualFixedBuyConditionChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorMutualFixedBuyConditionChangeLists.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //3)-1) ���M�莞��z���t�����s�̃��X�g�Ń��[�v����B
            Iterator l_iteratorUnitLists =
                l_lisMutualFixedBuyConditionUnitLists.iterator();
            boolean l_blnFlag = true;
            while (l_iteratorUnitLists.hasNext())
            {
                WEB3MutualFixedBuyConditionUnit l_unit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorUnitLists.next();

                //3)-1)-1) �ȉ��̏����Ŕ�r���āA��v����ꍇ
                //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h   ==
                //���M�莞��z���t�����s�D�����R�[�h
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_unit.mutualProductCode))
                {
                    //3)-1)-1)-1) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A�������́A�ύX�敪��
                    //�w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����
                    //�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ
                    if ((WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                        || ((WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv))
                            || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                            && !l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                    {
                        //3)-1)-1)-1)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                        //�莞��z���t�����ύX�e�[�u���̓��e�œ��M�莞��z���t�����s���㏑������B
                        //[�Z�b�g������e]
                        //�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A
                        if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                            || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                        {
                            //���t���z�i���X�j�F
                            //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j
                            if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                            {
                                l_unit.monthlyBuyAmount = null;
                            }
                            else
                            {
                                l_unit.monthlyBuyAmount =
                                    WEB3StringTypeUtility.formatNumber(
                                        l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                            }

                            //���t���z�i�ςݑ����j�F
                            //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                            if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                            {
                                l_unit.increaseBuyAmount = null;
                            }
                            else
                            {
                                l_unit.increaseBuyAmount =
                                    WEB3StringTypeUtility.formatNumber(
                                        l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                            }

                            //�ꎞ��~���t���O�Ffalse
                            l_unit.suspensionFlag = false;
                        }
                        //�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����
                        //�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A
                        else
                        {
                            //���t���z�i���X�j�F�@@0
                            l_unit.monthlyBuyAmount = "0";
                            //���t���z�i�ςݑ����j�F�@@0
                            l_unit.increaseBuyAmount = "0";
                            //�ꎞ��~���t���O�Ftrue 
                            l_unit.suspensionFlag = true;
                        }

                        //�K�p�J�n�N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                        l_unit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();
                        //���������N���F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                        l_unit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        //�m��������z�i�ςݑ����j�F
                        //�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A�@@
                        //  �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!=
                        //  �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j�̏ꍇ�A
                        //  �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j
                        //  ����ȊO�̏ꍇ�Anull
                        if (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                        {
                            l_unit.definiteIncreaseBuyAmount = null;
                        }
                        else if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                                    || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                        {
                            if (!WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals(
                                        WEB3StringTypeUtility.formatNumber(l_mfFixedBuyingChangeRow.getIncreaseBuyAmount())))
                            {
                                l_unit.definiteIncreaseBuyAmount =
                                    WEB3StringTypeUtility.formatNumber(
                                        l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                            }
                            else
                            {
                                l_unit.definiteIncreaseBuyAmount = null;
                            }
                        }
                        //�ύX�敪���u3�F�����v�܂��́u4�F�ꎞ��~�v�̏ꍇ�A
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j
                        else
                        {
                            l_unit.definiteIncreaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                        }
                    }

                    //3)-1)-1)-2) �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����
                    //�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j== null
                    //�܂��́@@0�@@�x �̏ꍇ�A
                    //�莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z���t�����s�̃��X�g����폜����B
                    if ((WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                        && (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull()
                            ||WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals("0")))
                    {
                        //3)-1)-1)-2)-1) ���M�莞��z���t�����s�̃��X�g����Y������s�I�u�W�F�N�g���폜����B
                        l_iteratorUnitLists.remove();
                    }

                    l_blnFlag = false;
                    //3)-1)-1)-3) ����.�莞��z���t�����ύX���X�g�̃��[�v�֖߂�B
                    break;
                }
            }

            if (!l_blnFlag)
            {
                continue;
            }

            //3)-2)�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�ύX�敪��
            //�u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�������́A
            //�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����
            //�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null 
            // ���� != 0 �x �̏ꍇ
            if ((WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                || ((WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv)
                    || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    && (!l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull()
                        && !WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals("0"))))
            {
                //3)-2)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //3)-2)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
                //�mget���M�����̈����n
                //�،���ЁF����.�،����
                //�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
                MutualFundProduct l_mutualFundProduct;
                try
                {
                    l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                        l_institution,
                        l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂��� ");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //3)-2)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                //���M�莞��z���t�����ύX�e�[�u���݂̂ɑ��݂���ꍇ�A
                //���M�莞��z���t�����ύX�e�[�u���̓��e�𓊐M�莞��z���t�����s�̃v���p�e�B�փZ�b�g����B
                if (l_mfFixedBuyingChangeRow != null)
                {
                    //[�Z�b�g������e]
                    //�����R�[�h�F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�����R�[�h
                    l_mutualFixedBuyConditionUnit.mutualProductCode =
                        l_mfFixedBuyingChangeRow.getProductCode();
                    //�������F�擾�������M�����}�X�^Row.get������
                    l_mutualFixedBuyConditionUnit.mutualProductName =
                        l_mutualFundProductRow.getStandardName();
                    //���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h
                    l_mutualFixedBuyConditionUnit.categoryCode =
                        l_mutualFundProductRow.getCategoryCode();

                    //�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                    {
                        //���t���z�i���X�j�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i���X�j
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }

                        //���t���z�i�ςݑ����j�F
                        //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }

                        //�ꎞ��~���t���O�Ffalse��ݒ�
                        l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                    }
                    //�ύX�敪���w�u3�F�����v�܂��́u4�F�ꎞ��~�v�x����
                    //�w�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!= null�x �̏ꍇ�A
                    else
                    {
                        //���t���z�i���X�j�F�@@0
                        l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "0";
                        //���t���z�i�ςݑ����j�F�@@0
                        l_mutualFixedBuyConditionUnit.increaseBuyAmount = "0";
                        //�ꎞ��~���t���O�Ftrue��ݒ�
                        l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                    }

                    //�����\�������F�擾�������M�����}�X�^Row.get�\������
                    if (l_mutualFundProductRow.getIndicationRankingIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundProductRow.getIndicationRanking());
                    }
                    //�K�p�J�n�N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                    l_mutualFixedBuyConditionUnit.validStartDate =
                        l_mfFixedBuyingChangeRow.getValidStartDate();
                    //�X�V�����Fnull
                    l_mutualFixedBuyConditionUnit.updateDate = null;
                    //���������N���F�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                    l_mutualFixedBuyConditionUnit.debitAccountYM =
                        new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                    //�m��������z�i�ςݑ����j�F
                    //�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ�A
                    //  �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j!=
                    //  �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j�̏ꍇ�A
                    //  �����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j
                    //  ����ȊO�̏ꍇ�Anull
                    if (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                    }
                    else if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                    {
                        if (!WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals(
                                    WEB3StringTypeUtility.formatNumber(l_mfFixedBuyingChangeRow.getIncreaseBuyAmount())))
                        {
                            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                        }
                    }
                    //�ύX�敪���u3�F�����v�܂��́u4�F�ꎞ��~�v�̏ꍇ�A
                    //�����D�莞��z���t�����ύX���X�g.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j
                    else
                    {
                        l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount =
                            WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                    }

                    //�ژ_�����{���`�F�b�N�Fnull
                    l_mutualFixedBuyConditionUnit.checkResult = null;
                }

                //3)-2)-4)  ���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
                l_lisMutualFixedBuyConditionUnitLists.add(l_mutualFixedBuyConditionUnit);
            }
        }

        //4) ���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisMutualFixedBuyConditionUnitLists.size()];
        l_lisMutualFixedBuyConditionUnitLists.toArray(l_mutualFixedBuyConditionUnits);

        //5) ���M�莞��z���t�����s�̔z������^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (merge�莞��z���t�����i�R�����\���j)<BR>
     * �R�����ڂ̒莞��z���t�����ύX���X�g�̓��e�Ɠ��M�莞��z���t�����s�i�Q�����ځj�̓��e���}�[�W����<BR>
     * ���M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * <BR>
     * 1)�@@�莞��z���t���ʃT�[�r�X���擾����B<BR>
     * <BR>
     * 2)�@@����.���M�莞��z���t�����s�̌��������[�v���A���M�莞��z���t�����s�̃��X�g���쐬����B<BR>
     * <BR>
     * �@@2)-1)�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@**** ���M�莞��z���t�����s(�Q������)�̓��e���R�����ڕ\���p�f�[�^�ɍX�V����B<BR>
     * �@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�@@�@@���������N���F�����D���M�莞��z���t�����s�D���������N���{�P<BR>
     * <BR>
     * �@@2-2)���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * 3)�@@����.�莞��z���t�����ύX���X�g�i�R�����ځj�Ń��[�v���A����.�莞��z���t�����ύX���X�g�i�R�����ځj��<BR>
     * �@@���e�𓊐M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g�ɔ��f����B<BR>
     * <BR>
     * �@@3)-1)�@@���M�莞��z���t�����s�̃��X�g�Ń��[�v����B<BR>
     * <BR>
     * �@@�@@�@@3)-1)-1)�@@�ȉ��̏����Ŕ�r���āA��v����ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�����R�[�h   ==<BR>
     * �@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-1)-1)-1)�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�ύX�敪��<BR>
     * �@@�@@�@@�@@�@@�@@�u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@3)-1)-1)-1)-1)�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u��(�R������)�̓��e�œ��M�莞��z���t�����s���㏑������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i���X�j�F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n�N���F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���������N���F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-1)-1)-2)�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�ύX�敪���u3�F�����v�܂��́u4�F�ꎞ��~�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z���t�����s�̃��X�g����폜����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-1)-1)-3)�@@����.�莞��z���t�����ύX���X�g�i�R�����ځj�̃��[�v�֖߂�B<BR>
     * <BR>
     * �@@3)-2)�@@��v���郊�X�g�����M�莞��z���t�s�ɑ��݂��Ȃ��ꍇ�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�ύX�敪���u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-2)-1)���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-2)-2)�@@�g�����M�����}�l�[�W���[.get���M����()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�mget���M�����̈����n<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-2)-3)�@@���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@**** �莞��z���t�����ύX�e�[�u���i�R�����ځj�̓��e�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@******************************************************************************************<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[�Z�b�g������e]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h�F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������F�擾�������M�����}�X�^Row.get������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i���X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i���X�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t���z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����\�������F�擾�������M�����}�X�^Row.get�\������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n�N���F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�X�V�����Fnull<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���������N���F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�m��������z�i�ςݑ����j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ژ_�����{���`�F�b�N�Fnull<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ꎞ��~���t���O�Ffalse<BR>
     * <BR>
     * �@@�@@�@@�@@�@@3)-2)-4)���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B<BR>
     * <BR>
     * 4)�@@���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B<BR>
     * <BR>
     * 5)�@@���M�莞��z���t�����s�̔z������^�[������B<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_mutualFixedBuyConditionUnits - (���M�莞��z���t�����s)<BR>
     * ���M�莞��z���t�����s<BR>
     * @@param l_lisMutualFixedBuyConditionChangeListThreeMonths - (�莞��z���t�����ύX���X�g�i�R�����ځj)<BR>
     * �莞��z���t�����ύX���X�g�i�R�����ځj<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionThreeMonth(
        Institution l_institution,
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits,
        List l_lisMutualFixedBuyConditionChangeListThreeMonths) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionThreeMonth("
            + "Institution, WEB3MutualFixedBuyConditionUnit[], List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisMutualFixedBuyConditionChangeListThreeMonths == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp =
            (FinApp)Services.getService(FinApp.class);

        //�g�����M�����}�l�[�W�����擾����B
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //1) �莞��z���t���ʃT�[�r�X���擾����B

        //2) ����.���M�莞��z���t�����s�̌��������[�v���A���M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g���쐬����B
        List l_lisMutualFixedBuyConditionUnitLists = new ArrayList();
        int l_intUnitsLength = 0;
        if (l_mutualFixedBuyConditionUnits != null)
        {
            l_intUnitsLength = l_mutualFixedBuyConditionUnits.length;
        }
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsThird =
            new WEB3MutualFixedBuyConditionUnit[l_intUnitsLength];
        for (int i = 0; i < l_intUnitsLength; i++)
        {
            l_mutualFixedBuyConditionUnitsThird[i] =
                new WEB3MutualFixedBuyConditionUnit();
            //2)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
            //���M�莞��z���t�����s(�Q������)�̓��e���R�����ڕ\���p�f�[�^�ɍX�V����B
            //[�Z�b�g������e]
            //���������N���F�����D���M�莞��z���t�����s�D���������N���{�P
            l_mutualFixedBuyConditionUnitsThird[i].debitAccountYM =
                new Timestamp(WEB3DateUtility.addMonth(
                    l_mutualFixedBuyConditionUnits[i].debitAccountYM,
                    1).getTime());

            //�m��������z�i�ςݑ����j�Fnull
            l_mutualFixedBuyConditionUnitsThird[i].definiteIncreaseBuyAmount = null;

            l_mutualFixedBuyConditionUnitsThird[i].categoryCode =
                l_mutualFixedBuyConditionUnits[i].categoryCode;
            l_mutualFixedBuyConditionUnitsThird[i].checkResult =
                l_mutualFixedBuyConditionUnits[i].checkResult;
            l_mutualFixedBuyConditionUnitsThird[i].displayOrder =
                l_mutualFixedBuyConditionUnits[i].displayOrder;
            l_mutualFixedBuyConditionUnitsThird[i].increaseBuyAmount =
                l_mutualFixedBuyConditionUnits[i].increaseBuyAmount;
            l_mutualFixedBuyConditionUnitsThird[i].monthlyBuyAmount =
                l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount;
            l_mutualFixedBuyConditionUnitsThird[i].mutualProductCode =
                l_mutualFixedBuyConditionUnits[i].mutualProductCode;
            l_mutualFixedBuyConditionUnitsThird[i].mutualProductName =
                l_mutualFixedBuyConditionUnits[i].mutualProductName;
            l_mutualFixedBuyConditionUnitsThird[i].suspensionFlag =
                l_mutualFixedBuyConditionUnits[i].suspensionFlag;
            l_mutualFixedBuyConditionUnitsThird[i].updateDate =
                l_mutualFixedBuyConditionUnits[i].updateDate;
            l_mutualFixedBuyConditionUnitsThird[i].validStartDate =
                l_mutualFixedBuyConditionUnits[i].validStartDate;
            //2-2)���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
            l_lisMutualFixedBuyConditionUnitLists.add(l_mutualFixedBuyConditionUnitsThird[i]);
        }

        //3) ����.�莞��z���t�����ύX���X�g�i�R�����ځj�Ń��[�v���A����.�莞��z���t�����ύX���X�g�i�R�����ځj��
        //���e�𓊐M�莞��z���t�����s�I�u�W�F�N�g�̃��X�g�ɔ��f����B
        Iterator l_iteratorListThreeMonths =
            l_lisMutualFixedBuyConditionChangeListThreeMonths.iterator();
        while (l_iteratorListThreeMonths.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorListThreeMonths.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //3)-1) ���M�莞��z���t�����s�̃��X�g�Ń��[�v����B
            Iterator l_iteratorUnitLists =
                l_lisMutualFixedBuyConditionUnitLists.iterator();
            boolean l_blnFlag = true;
            while (l_iteratorUnitLists.hasNext())
            {
                WEB3MutualFixedBuyConditionUnit l_unit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorUnitLists.next();

                //3)-1)-1) �ȉ��̏����Ŕ�r���āA��v����ꍇ
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�����R�[�h   ==
                //���M�莞��z���t�����s�D�����R�[�h
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_unit.mutualProductCode))
                {
                    //3)-1)-1)-1) �����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                    {
                        //3)-1)-1)-1)-1) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                        //�莞��z���t�����ύX�e�[�u��(�R������)�̓��e�œ��M�莞��z���t�����s���㏑������B
                        //[�Z�b�g������e]
                        //���t���z�i���X�j�F
                        //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i���X�j
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_unit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_unit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }
                        //���t���z�i�ςݑ����j�F
                        //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_unit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_unit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }
                        //�K�p�J�n�N���F
                        //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                        l_unit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();
                        //���������N���F
                        //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                        l_unit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        //�ꎞ��~���t���O:
                        //false
                        l_unit.suspensionFlag = false;
                    }

                    //3)-1)-1)-2) �����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�ύX�敪��
                    //�u3�F�����v�܂��́u4�F�ꎞ��~�v�̏ꍇ
                    if (WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        //�莞��z���t�����ύX�e�[�u���̓��e�����ɓ��M�莞��z���t�����s�̃��X�g����폜����B
                        l_iteratorUnitLists.remove();
                    }

                    l_blnFlag = false;
                    //3)-1)-1)-3) ����.�莞��z���t�����ύX���X�g�i�R�����ځj�̃��[�v�֖߂�B
                    break;
                }
            }

            if (!l_blnFlag)
            {
                continue;
            }

            //3)-2) ��v���郊�X�g�����M�莞��z���t�s�ɑ��݂��Ȃ��ꍇ�@@����
            //�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�ύX�敪��
            //�u1�F�ǉ��v�܂��́u2�F�ύX�v�̏ꍇ
            if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
            {
                //3)-2)-1)���M�莞��z���t�����s�I�u�W�F�N�g�𐶐�����B
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //3)-2)-2) �g�����M�����}�l�[�W���[.get���M����()���R�[������B
                //�mget���M�����̈����n
                //�،���ЁF����.�،����
                //�����R�[�h�F
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�����R�[�h
                MutualFundProduct l_mutualFundProduct = null;
                try
                {
                    l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                        l_institution,
                        l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂��� ");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //3)-2)-3) ���M�莞��z���t�����s�I�u�W�F�N�g�̃v���p�e�B�Z�b�g�B
                //�莞��z���t�����ύX�e�[�u���i�R�����ځj�̓��e�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
                //[�Z�b�g������e]
                //�����R�[�h�F�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�����R�[�h
                l_mutualFixedBuyConditionUnit.mutualProductCode =
                    l_mutualFundProductRow.getProductCode();
                //�������F�擾�������M�����}�X�^Row.get������
                l_mutualFixedBuyConditionUnit.mutualProductName =
                    l_mutualFundProductRow.getStandardName();
                //���M�����J�e�S���[�R�[�h�F�擾�������M�����}�X�^Row.get�J�e�S���R�[�h
                l_mutualFixedBuyConditionUnit.categoryCode =
                    l_mutualFundProductRow.getCategoryCode();
                //���t���z�i���X�j�F
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i���X�j
                if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                }
                //���t���z�i�ςݑ����j�F
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D���t���z�i�ςݑ����j
                if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                }
                //�����\�������F�擾�������M�����}�X�^Row.get�\������
                if (l_mutualFundProductRow.getIndicationRankingIsNull())
                {
                    l_mutualFixedBuyConditionUnit.displayOrder = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.displayOrder =
                        WEB3StringTypeUtility.formatNumber(
                            l_mutualFundProductRow.getIndicationRanking());
                }
                //�K�p�J�n�N���F
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                l_mutualFixedBuyConditionUnit.validStartDate =
                    l_mfFixedBuyingChangeRow.getValidStartDate();
                //�X�V�����Fnull
                l_mutualFixedBuyConditionUnit.updateDate = null;
                //���������N���F
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�K�p�J�n�N��
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                //�m��������z�i�ςݑ����j�F
                //�����D�莞��z���t�����ύX���X�g�i�R�����ځj.�莞��z���t�����ύXRow�D�m��������t���z�i�ςݑ����j
                if (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                }
                //�ژ_�����{���`�F�b�N�Fnull
                l_mutualFixedBuyConditionUnit.checkResult = null;
                //�ꎞ��~���t���O�Ffalse
                l_mutualFixedBuyConditionUnit.suspensionFlag = false;

                //3)-2)-4)���M�莞��z���t�����s�I�u�W�F�N�g�𓊐M�莞��z���t�����s�̃��X�g�֒ǉ�����B
                l_lisMutualFixedBuyConditionUnitLists.add(
                    l_mutualFixedBuyConditionUnit);
            }
        }

        //4) ���M�莞��z���t�����s�̃��X�g���瓊�M�莞��z���t�����s�̔z����쐬����B
        WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisMutualFixedBuyConditionUnitLists.size()];
        l_lisMutualFixedBuyConditionUnitLists.toArray(l_conditionUnits);

        //5) ���M�莞��z���t�����s�̔z������^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_conditionUnits;
    }

    /**
     * (get�w��N���莞��z���t�������X�g)<BR>
     * �����D�莞��z���t�������X�g����A���������N���������D�w��N���Ɠ��������R�[�h�𒊏o���A<BR>
     * ���X�g�ɂ��ĕԋp����B<BR>
     * @@param l_lisFixedBuyConditionLists - (�莞��z���t�������X�g)<BR>
     * �莞��z���t�������X�g<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getSelectMYFixedBuyConditionList(
        List l_lisFixedBuyConditionLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSelectMYFixedBuyConditionList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionLists == null
            || l_datSelectMY == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        List l_lisSelectYMLists = new ArrayList();
        Iterator l_iteratorConditionLists = l_lisFixedBuyConditionLists.iterator();
        while (l_iteratorConditionLists.hasNext())
        {
            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorConditionLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingCondRow.getDrawDate(),
                    l_datSelectMY);
            if (l_intCompareResult == 0)
            {
                l_lisSelectYMLists.add(l_mfFixedBuyingCondRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSelectYMLists;
    }

    /**
     * (get�w��N���莞��z���t�����ύX���X�g)<BR>
     * �����D�莞��z���t�����ύX���X�g����A�K�p�J�n�N���������D�w��N���Ɠ��������R�[�h�𒊏o���A<BR>
     * ���X�g�ɂ��Ă�ԋp����B<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (�莞��z���t�����ύX���X�g)<BR>
     * �莞��z���t�����ύX���X�g<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getSelectMYFixedBuyConditionChangeList(
        List l_lisFixedBuyConditionChangeLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSelectMYFixedBuyConditionChangeList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionChangeLists == null
            || l_datSelectMY == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        List l_lisSelectYMChangeLists = new ArrayList();
        Iterator l_iteratorConditionChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        while (l_iteratorConditionChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorConditionChangeLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingChangeRow.getValidStartDate(),
                    l_datSelectMY);
            if (l_intCompareResult == 0)
            {
                l_lisSelectYMChangeLists.add(l_mfFixedBuyingChangeRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSelectYMChangeLists;
    }


    /**
     * (get�莞��z���t���z���v)<BR>
     * get�莞��z���t���z���v<BR>
     * �莞��z���t���z�̍��v���v�Z����B<BR>
     * �R�ԖڃI�u�W�F�N�g�́A3�����\�����ɉ�ʕ\������B<BR>
     * <BR>
     * �P�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B�i�P�Ԗځj<BR>
     * �Q�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B�i�Q�Ԗځj<BR>
     * �R�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B�i�R�Ԗځj<BR>
     * <BR>
     * �S�D�ϐ�work�m��������z���v�i�ςݑ����j�Z�o�t���O�i�P�Ԗځj���쐬���A<BR>
     * �@@�@@false���Z�b�g����B<BR>
     * �T�D�ϐ�work�m��������z���v�i�ςݑ����j�Z�o�t���O�i�Q�Ԗځj���쐬���A<BR>
     * �@@�@@false���Z�b�g����B<BR>
     * �U�D�ϐ�work�m��������z���v�i�ςݑ����j�Z�o�t���O�i�R�Ԗځj���쐬���A<BR>
     * �@@�@@false���Z�b�g����B<BR>
     * <BR>
     * �V�D���M�莞��z���t�����s[]��1���R�[�h�ڂ̓��M�莞��z���t�����s.���������N����<BR>
     * �@@�@@�ϐ�work���������N���ɃZ�b�g����B<BR>
     * <BR>
     * �W�D���M�莞��z���t�����s[]�̌������A�ȉ��̏������J�Ԃ��B<BR>
     * <BR>
     * �@@�W-�P�D���M�莞��z���t�����s.���������N���@@=�@@work���������N���̏ꍇ<BR>
     * <BR>
     * �@@�@@�W-�P-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.���X���v =<BR>
     * �@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�P�Ԗځj.���X���v +<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s.���t���z�i���X�j<BR>
     * <BR>
     * �@@�@@�W-�P-�Q�D���M�莞��z���t���z���v�i�P�Ԗځj.�ςݑ������v =<BR>
     * �@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�P�Ԗځj.�ςݑ������v +<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s.���t���z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�P-�R�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ���@@null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�P-�R-�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�P�Ԗځj��true���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�P-�R-�Q�D���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j +<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s.�m��������z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�P-�S�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ==�@@null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�P-�S-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�P-�T�D���M�莞��z���t���z���v�i�P�Ԗځj.���������N�� = ���������̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�P-�T-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.���������N�� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@���M�莞��z���t�����s.���������N��<BR>
     * <BR>
     * �@@�W-�Q�@@���M�莞��z���t�����s.���������N���@@==�@@work���������N��+1�̏ꍇ<BR>
     * <BR>
     * �@@�@@�W-�Q-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.���X���v =<BR>
     * �@@�@@�@@�@@���M�莞��z���t���z���v�i�Q�Ԗځj.���X���v + ���M�莞��z���t�����s.���t���z�i���X�j<BR>
     * <BR>
     * �@@�@@�W-�Q-�Q�D���M�莞��z���t���z���v�i�Q�Ԗځj.�ςݑ������v =<BR>
     * �@@�@@�@@�@@���M�莞��z���t���z���v�i�Q�Ԗځj.�ςݑ������v<BR>
     * �@@�@@�@@�@@�@@�@@+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�Q-�R�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ���@@null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�Q-�R-�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�Q�Ԗځj��true���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�Q-�R-�Q�D���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���M�莞��z���t�����s.�m��������z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�Q-�S�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ==�@@null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�Q-�S-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�Q-�T�D���M�莞��z���t���z���v�i�Q�Ԗځj.���������N�� = ���������̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�Q-�T-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.���������N�� =<BR>
     * �@@�@@�@@�@@�@@�@@���M�莞��z���t�����s.���������N��<BR>
     * <BR>
     * �@@�W-�R�@@���M�莞��z���t�����s.���������N���@@==�@@work���������N��+2�̏ꍇ<BR>
     * <BR>
     * �@@�@@�W-�R-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.���X���v =<BR>
     * �@@�@@�@@ ���M�莞��z���t���z���v�i�R�Ԗځj.���X���v + ���M�莞��z���t�����s.���t���z�i���X�j<BR>
     * <BR>
     * �@@�@@�W-�R-�Q�D���M�莞��z���t���z���v�i�R�Ԗځj.�ςݑ������v =<BR>
     * �@@�@@�@@ ���M�莞��z���t���z���v�i�R�Ԗځj.�ςݑ������v + ���M�莞��z���t�����s.���t���z�i�ςݑ������v�j<BR>
     * <BR>
     * �@@�@@�W-�R-�R�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ���@@null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�R-�R-�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�R�Ԗځj��true���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�R-�R-�Q�D���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j =<BR>
     * �@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���M�莞��z���t�����s.�m��������z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�R-�S�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ==�@@null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�R-�S-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j<BR>
     * <BR>
     * �@@�@@�W-�R-�T�D���M�莞��z���t���z���v�i�R�Ԗځj.���������N�� = ���������̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�W-�R-�T-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.���������N�� =<BR>
     * �@@�@@�@@�@@�@@�@@���M�莞��z���t�����s.���������N��<BR>
     * <BR>
     * �X�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�P�Ԗځj == false �̏ꍇ<BR>
     * <BR>
     * �@@�X-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j = null ���Z�b�g����B<BR>
     * <BR>
     * �P�O�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�Q�Ԗځj == false �̏ꍇ<BR>
     * <BR>
     * �@@�P�O-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j = null ���Z�b�g����B<BR>
     * <BR>
     * �P�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�R�Ԗځj == false �̏ꍇ<BR>
     * <BR>
     * �@@�P�P-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j = null ���Z�b�g����B<BR>
     * <BR>
     * �P�Q�D�߂�l �莞��z���t���z���v[0]�ɓ��M�莞��z���t���z���v�i�P�Ԗځj���Z�b�g���A<BR>
     * �@@�@@�@@�߂�l �莞��z���t���z���v[1]�ɓ��M�莞��z���t���z���v�i�Q�Ԗځj���Z�b�g���A<BR>
     * �@@�@@�@@�߂�l �莞��z���t���z���v[2]�ɓ��M�莞��z���t���z���v�i�R�Ԗځj���Z�b�g����B<BR>
     * �@@�@@�@@�i*)null�łȂ��ꍇ�A�Z�b�g����B<BR>
     * @@param l_mutualFixedBuyConditionUnits - (���M�莞��z���t�����s[])<BR>
     * ���M�莞��z���t�����s[]<BR>
     * @@return WEB3MutualFixedBuyTotalUnit[]<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyTotalUnit[] getFixedBuyTotalUnit(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFixedBuyTotalUnit(WEB3MutualFixedBuyConditionUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyConditionUnits == null
            || l_mutualFixedBuyConditionUnits.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B�i�P�Ԗځj
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnitOne = new WEB3MutualFixedBuyTotalUnit();

        // �Q�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B�i�Q�Ԗځj
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnitTwo = new WEB3MutualFixedBuyTotalUnit();

        //�R�D���M�莞��z���t���z���v�I�u�W�F�N�g�𐶐�����B�i�R�Ԗځj
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnitThree = new WEB3MutualFixedBuyTotalUnit();

        //�S�D�ϐ�work�m��������z���v�i�ςݑ����j�Z�o�t���O�i�P�Ԗځj���쐬���Afalse���Z�b�g����B
        boolean l_blnIsDefiniteIncreaseBATotalFlagOne = false;
        //�T�D�ϐ�work�m��������z���v�i�ςݑ����j�Z�o�t���O�i�Q�Ԗځj���쐬���Afalse���Z�b�g����B
        boolean l_blnIsDefiniteIncreaseBATotalFlagTwo = false;
        //�U�D�ϐ�work�m��������z���v�i�ςݑ����j�Z�o�t���O�i�R�Ԗځj���쐬���Afalse���Z�b�g����B
        boolean l_blnIsDefiniteIncreaseBATotalFlagThree = false;
        //�V�D���M�莞��z���t�����s[]��1���R�[�h�ڂ�
        //���M�莞��z���t�����s.���������N����ϐ�work���������N���ɃZ�b�g����B
        Date l_datDebitAccountYM = l_mutualFixedBuyConditionUnits[0].debitAccountYM;

        //�W�D���M�莞��z���t�����s[]�̌������A�ȉ��̏������J�Ԃ��B
        int l_intLength = l_mutualFixedBuyConditionUnits.length;
        for (int i = 0; i < l_intLength; i ++)
        {
            //�W-�P�D���M�莞��z���t�����s.���������N���@@=�@@work���������N���̏ꍇ
            int l_intCompare = WEB3DateUtility.compareToMonth(
                l_datDebitAccountYM, l_mutualFixedBuyConditionUnits[i].debitAccountYM);

            if (l_intCompare == 0)
            {
                //�W-�P-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.���X���v =
                //���M�莞��z���t���z���v�i�P�Ԗځj.���X���v
                //+ ���M�莞��z���t�����s.���t���z�i���X�j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.monthlyBATotal))
                {
                    l_mfBuyTotalUnitOne.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnitOne.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitOne.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //�W-�P-�Q�D���M�莞��z���t���z���v�i�P�Ԗځj.�ςݑ������v =
                //���M�莞��z���t���z���v�i�P�Ԗځj.�ςݑ������v
                //+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.increaseBATotal))
                {
                    l_mfBuyTotalUnitOne.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }

                l_mfBuyTotalUnitOne.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitOne.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //�W-�P-�R�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ���@@null�̏ꍇ
                if (l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount != null)
                {
                    //�W-�P-�R-�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�P�Ԗځj��true���Z�b�g����B
                    l_blnIsDefiniteIncreaseBATotalFlagOne = true;
                    //�W-�P-�R-�Q�D���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j =
                    //���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j
                    //+ ���M�莞��z���t�����s.�m��������z�i�ςݑ����j
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitOne.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitOne.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitOne.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount));
                }
                //�W-�P-�S�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ==�@@null�̏ꍇ
                else
                {
                    //�W-�P-�S-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j =
                    //���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j
                    //+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitOne.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                    {
                        l_mfBuyTotalUnitOne.definiteIncreaseBATotal = "0";
                    }

                    l_mfBuyTotalUnitOne.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitOne.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));
                }

                //�W-�P-�T�D���M�莞��z���t���z���v�i�P�Ԗځj.���������N�� = ���������̏ꍇ
                if (l_mfBuyTotalUnitOne.debitAccountYM == null)
                {
                    //�W-�P-�T-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.���������N�� =
                    //�@@���M�莞��z���t�����s.���������N��
                    l_mfBuyTotalUnitOne.debitAccountYM = l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }

            //�W-�Q�@@���M�莞��z���t�����s.���������N���@@==�@@work���������N��+1�̏ꍇ
            l_intCompare = WEB3DateUtility.compareToMonth(
                WEB3DateUtility.addMonth(l_datDebitAccountYM, 1),
                l_mutualFixedBuyConditionUnits[i].debitAccountYM);
            if (l_intCompare == 0)
            {
                //�W-�Q-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.���X���v =
                //���M�莞��z���t���z���v�i�Q�Ԗځj.���X���v + ���M�莞��z���t�����s.���t���z�i���X�j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.monthlyBATotal))
                {
                    l_mfBuyTotalUnitTwo.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnitTwo.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitTwo.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //�W-�Q-�Q�D���M�莞��z���t���z���v�i�Q�Ԗځj.�ςݑ������v =
                //���M�莞��z���t���z���v�i�Q�Ԗځj.�ςݑ������v
                //+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.increaseBATotal))
                {
                    l_mfBuyTotalUnitTwo.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }

                l_mfBuyTotalUnitTwo.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitTwo.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //�W-�Q-�R�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ���@@null�̏ꍇ
                if (l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount != null)
                {
                    //�W-�Q-�R-�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�Q�Ԗځj��true���Z�b�g����B
                    l_blnIsDefiniteIncreaseBATotalFlagTwo = true;
                    //�W-�Q-�R-�Q�D���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j =
                    //���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j
                    //+ ���M�莞��z���t�����s.�m��������z�i�ςݑ����j
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount));
                }

                //�W-�Q-�S�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ==�@@null�̏ꍇ
                else
                {
                    //�W-�Q-�S-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j =
                    //���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j
                    //+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                    {
                        l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = "0";
                    }

                    l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));
                }

                //�W-�Q-�T�D���M�莞��z���t���z���v�i�Q�Ԗځj.���������N�� = ���������̏ꍇ
                if (l_mfBuyTotalUnitTwo.debitAccountYM == null)
                {
                    //�W-�Q-�T-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.���������N�� =
                    //���M�莞��z���t�����s.���������N��
                    l_mfBuyTotalUnitTwo.debitAccountYM =
                        l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }

            //�W-�R�@@���M�莞��z���t�����s.���������N���@@==�@@work���������N��+2�̏ꍇ
            l_intCompare = WEB3DateUtility.compareToMonth(
                WEB3DateUtility.addMonth(l_datDebitAccountYM, 2),
                l_mutualFixedBuyConditionUnits[i].debitAccountYM);
            if (l_intCompare == 0)
            {
                //�W-�R-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.���X���v =
                //���M�莞��z���t���z���v�i�R�Ԗځj.���X���v
                //+ ���M�莞��z���t�����s.���t���z�i���X�j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.monthlyBATotal))
                {
                    l_mfBuyTotalUnitThree.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnitThree.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitThree.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //�W-�R-�Q�D���M�莞��z���t���z���v�i�R�Ԗځj.�ςݑ������v =
                //���M�莞��z���t���z���v�i�R�Ԗځj.�ςݑ������v
                //+ ���M�莞��z���t�����s.���t���z�i�ςݑ������v�j
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.increaseBATotal))
                {
                    l_mfBuyTotalUnitThree.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }

                l_mfBuyTotalUnitThree.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitThree.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //�W-�R-�R�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ���@@null�̏ꍇ
                if (l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount != null)
                {
                    //�W-�R-�R-�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�R�Ԗځj��true���Z�b�g����B
                    l_blnIsDefiniteIncreaseBATotalFlagThree = true;
                    //�W-�R-�R-�Q�D���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j =
                    //���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j
                    //+ ���M�莞��z���t�����s.�m��������z�i�ςݑ����j
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitThree.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitThree.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitThree.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount));
                }

                //�W-�R-�S�D���M�莞��z���t�����s.�m��������z�i�ςݑ����j ==�@@null�̏ꍇ
                else
                {
                    //�W-�R-�S-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j =
                    //���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j
                    //+ ���M�莞��z���t�����s.���t���z�i�ςݑ����j
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitThree.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitThree.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitThree.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));
                }

                //�W-�R-�T�D���M�莞��z���t���z���v�i�R�Ԗځj.���������N�� = ���������̏ꍇ
                if (l_mfBuyTotalUnitThree.debitAccountYM == null)
                {
                    //�W-�R-�T-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.���������N�� =
                    //���M�莞��z���t�����s.���������N��
                    l_mfBuyTotalUnitThree.debitAccountYM =
                        l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }
        }

        //�X�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�P�Ԗځj == false �̏ꍇ
        //�X-�P�D���M�莞��z���t���z���v�i�P�Ԗځj.�m��������z���v�i�ςݑ����j = null ���Z�b�g����B
        if (!l_blnIsDefiniteIncreaseBATotalFlagOne)
        {
            l_mfBuyTotalUnitOne.definiteIncreaseBATotal = null;
        }

        //�P�O�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�Q�Ԗځj == false �̏ꍇ
        //�P�O-�P�D���M�莞��z���t���z���v�i�Q�Ԗځj.�m��������z���v�i�ςݑ����j = null ���Z�b�g����B
        if (!l_blnIsDefiniteIncreaseBATotalFlagTwo)
        {
            l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = null;
        }

        //�P�P�Dwork�m��������z���v�i�ςݑ����j�Z�o�t���O�i�R�Ԗځj == false �̏ꍇ
        //�P�P-�P�D���M�莞��z���t���z���v�i�R�Ԗځj.�m��������z���v�i�ςݑ����j = null ���Z�b�g����B
        if (!l_blnIsDefiniteIncreaseBATotalFlagThree)
        {
            l_mfBuyTotalUnitThree.definiteIncreaseBATotal = null;
        }

        //�P�Q�D�߂�l �莞��z���t���z���v[0]�ɓ��M�莞��z���t���z���v�i�P�Ԗځj���Z�b�g���A
        //�߂�l �莞��z���t���z���v[1]�ɓ��M�莞��z���t���z���v�i�Q�Ԗځj���Z�b�g���A
        //�߂�l �莞��z���t���z���v[2]�ɓ��M�莞��z���t���z���v�i�R�Ԗځj���Z�b�g����B
        //�i*)null�łȂ��ꍇ�A�Z�b�g����B
        List l_lisMutualFixedBuyTotalUnits = new ArrayList();
        l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnitOne);
        if (l_mfBuyTotalUnitTwo.debitAccountYM != null
            && l_mfBuyTotalUnitTwo.increaseBATotal != null)
        {
            l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnitTwo);
        }

        if (l_mfBuyTotalUnitThree.debitAccountYM != null
            && l_mfBuyTotalUnitThree.increaseBATotal != null)
        {
            l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnitThree);
        }

        WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits =
            new WEB3MutualFixedBuyTotalUnit[l_lisMutualFixedBuyTotalUnits.size()];

        l_lisMutualFixedBuyTotalUnits.toArray(l_mutualFixedBuyTotalUnits);
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyTotalUnits;
    }

    /**
     * (get�������莞��z���t�������X�g)<BR>
     * �����D�莞��z���t�������X�g����A���������N���������D�w��N���̏����Ɉ�v���郌�R�[�h�𒊏o���A<BR>
     * ���X�g�ɂ��ĕԋp����B<BR>
     * @@param l_lisFixedBuyConditionLists - (�莞��z���t�������X�g)<BR>
     * �莞��z���t�������X�g<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getFutureFixedBuyConditionList(
        List l_lisFixedBuyConditionLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFutureFixedBuyConditionList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionLists == null
            || l_datSelectMY == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        List l_lisFutureLists = new ArrayList();
        Iterator l_iteratorConditionLists =
            l_lisFixedBuyConditionLists.iterator();
        while (l_iteratorConditionLists.hasNext())
        {
            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorConditionLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingCondRow.getDrawDate(),
                    l_datSelectMY);
            if (l_intCompareResult > 0)
            {
                l_lisFutureLists.add(l_mfFixedBuyingCondRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFutureLists;
    }

    /**
     * (get�������莞��z���t�����ύX���X�g)<BR>
     * �����D�莞��z���t�����ύX���X�g����A�K�p�J�n�N���������D�w��N���̏����Ɉ�v���郌�R�[�h�𒊏o���A<BR>
     * ���X�g�ɂ��ĕԋp����B<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (�莞��z���t�����ύX���X�g)<BR>
     * �莞��z���t�����ύX���X�g<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getFutureFixedBuyConditionChangeList(
        List l_lisFixedBuyConditionChangeLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFutureFixedBuyConditionChangeList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionChangeLists == null
            || l_datSelectMY == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        List l_lisFutureChangeLists = new ArrayList();
        Iterator l_iteratorConditionChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        while (l_iteratorConditionChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorConditionChangeLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingChangeRow.getValidStartDate(),
                    l_datSelectMY);
            if (l_intCompareResult > 0)
            {
                l_lisFutureChangeLists.add(l_mfFixedBuyingChangeRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFutureChangeLists;
    }
}
@
