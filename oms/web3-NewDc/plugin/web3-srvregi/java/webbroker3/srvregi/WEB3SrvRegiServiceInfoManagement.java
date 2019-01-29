head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceInfoManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���Ǘ�(WEB3SrvRegiServiceInfoManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �s�p (���u) �V�K�쐬
Revesion History : 2007/06/05 �����F (���u) ���f��240
Revesion History : 2007/06/21 �����Q (���u) ���f��269
Revesion History : 2007/06/22 �����Q (���u) ���f��271
Revesion History : 2007/07/24 �h�C (���u) ���f��294
Revesion History : 2007/11/01 �����F (���u) ���f��305
Revesion History : 2008/02/18 ���g (���u) ���f��310,313
Revesion History : 2008/03/03 ���g �d�l�ύX ���f��330,343
Revesion History : 2008/03/20 ���n�m �d�l�ύX ���f��348
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvAppliAttributeProcDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoTranHistServiceResultDef;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiCommCondMasterRow;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiDealingCommRow;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.define.WEB3SrvRegiOfferingDivAppendDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���Ǘ�)<BR>
 * �T�[�r�X���Ǘ��N���X<BR>
 *
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3SrvRegiServiceInfoManagement
{


    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceInfoManagement.class);

    /**
     * @@roseuid 416F4A9E036B
     */
    public WEB3SrvRegiServiceInfoManagement()
    {

    }

    /**
     * (get�T�[�r�X�}�X�^�[)<BR>
     * �����Ŏw�肳�ꂽ�،���ЃR�[�h�A�T�[�r�X�敪�ɊY������<BR>
     * �T�[�r�X�}�X�^�[�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * 1) �T�[�r�X�}�X�^�[�e�[�u�����������A�T�[�r�X�}�X�^�[Params���擾����B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 2) �T�[�r�X�}�X�^�[Params���擾�ł��Ȃ��ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00982<BR>
     * <BR>
     * 3) �T�[�r�X�}�X�^�[�I�u�W�F�N�g�𐶐�����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�}�X�^�[Row: �擾�����T�[�r�X�}�X�^�[Params<BR>
     * <BR>
     * 4) ����.is�s���b�N=true�̏ꍇ<BR>
     * �@@�T�[�r�X�}�X�^�[�I�u�W�F�N�g.createForUpdateParams( )���R�[������B<BR>
     * <BR>
     * 5) �T�[�r�X�}�X�^�[�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceMaster
     * @@roseuid 40F6563C02B1
     */
    public WEB3SrvRegiServiceMaster getSrvMaster(String l_strInstitutionCode, String l_strSrvDiv, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvMaster(String, String, boolean) ";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceMaster l_serviceMaster = null;

        try
        {
            //according to the QA of WEB3-SRVREGI-A-CD-0026
            //1) �T�[�r�X�}�X�^�[�e�[�u�����������A�T�[�r�X�}�X�^�[Params���擾����B
            String l_strWhere = " institution_code = ? and srv_div = ? ";

            Object[] l_obj = {l_strInstitutionCode, l_strSrvDiv};

            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisServiceMasterRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisServiceMasterRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    " FOR UPDATE ",
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisServiceMasterRowList =
                    l_queryProcessor.doFindAllQuery(SrvRegiMasterRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
            }

            //2) �T�[�r�X�}�X�^�[Params���擾�ł��Ȃ��ꍇ�͗�O���X���[����B
            if (l_lisServiceMasterRowList == null || l_lisServiceMasterRowList.size() == 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            int l_intServiceMasterRowCnt = l_lisServiceMasterRowList.size();

            if (l_intServiceMasterRowCnt > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //3) �T�[�r�X�}�X�^�[�I�u�W�F�N�g�𐶐�����B
            l_serviceMaster = new WEB3SrvRegiServiceMaster((SrvRegiMasterRow)l_lisServiceMasterRowList.get(0));

            //4) ����.is�s���b�N=true�̏ꍇ
            if (l_blnIsRowLock)
            {
                log.debug("isRowLock = true");
                l_serviceMaster.createForUpdateParams();
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        //5) �T�[�r�X�}�X�^�[�I�u�W�F�N�g��ԋp����B
        return l_serviceMaster;
    }

    /**
     * (get�T�[�r�X�}�X�^�[�ꗗ)<BR>
     * �T�[�r�X�}�X�^�[�̈ꗗ�����o���A�ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X�}�X�^�[�e�[�u���v�����o����B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * �@@���j����.�\���敪="�S��"�ȊO�̏ꍇ�A�ȉ��������Ɋ܂߂�<BR>
     * �@@and �\���敪=����.�\���敪<BR>
     * [���я�]<BR>
     * �@@�T�[�r�X�敪�@@����<BR>
     * <BR>
     * 2) �������ʂ̌������A�ȉ����J��Ԃ��B<BR>
     *  2-1) �������ʂ̃T�[�r�X�}�X�^�[Params�������ɁA<BR>
     * �@@�@@�T�[�r�X�}�X�^�[�N���X�̃R���X�g���N�^���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�}�X�^�[Row: �擾�����T�[�r�X�}�X�^�[Params<BR>
     * <BR>
     *  2-2) ���������T�[�r�X�}�X�^�[�I�u�W�F�N�g��z��ɒǉ�����B<BR>
     * <BR>
     * 3) 2)�ō쐬�����T�[�r�X�}�X�^�[�N���X�̔z���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strOfferingDiv - (�\���敪)<BR>
     * 0:�s�v�@@1:�v�@@2:�S��<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceMaster[ ]
     * @@roseuid 4108E2E0025C
     */
    public WEB3SrvRegiServiceMaster[] getSrvMasterList(String l_strInstitutionCode, String l_strOfferingDiv) throws WEB3BaseException
    {
        WEB3SrvRegiServiceMaster[] l_serviceMasters = null;
        final String STR_METHOD_NAME = " getSrvMasterList(String, String) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1) �ȉ��̏����Łu�T�[�r�X�}�X�^�[�e�[�u���v�����o����B
            String l_strWhere = " institution_code = ? ";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisServiceMasterRowList = null;

            if (!WEB3SrvRegiOfferingDivAppendDef.EVERYTHING.equals(l_strOfferingDiv))
            {
                l_strWhere += " and offering_div = ? ";
                Object[] l_obj = {l_strInstitutionCode, l_strOfferingDiv};
                l_lisServiceMasterRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    " srv_div asc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                Object[] l_obj = {l_strInstitutionCode};
                l_lisServiceMasterRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    " srv_div asc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException
            }

            if (l_lisServiceMasterRowList != null)
            {

                int l_intServiceMasterRowCnt = l_lisServiceMasterRowList.size();

                //2) �������ʂ̌������A�ȉ����J��Ԃ��B
                l_serviceMasters = new WEB3SrvRegiServiceMaster[l_intServiceMasterRowCnt];

                for (int i = 0; i < l_intServiceMasterRowCnt; i++)
                {
                    //2-1) �������ʂ̃T�[�r�X�}�X�^�[Params�������ɁA
                    //2-2) ���������T�[�r�X�}�X�^�[�I�u�W�F�N�g��z��ɒǉ�����B
                    l_serviceMasters[i] =
                        new WEB3SrvRegiServiceMaster((SrvRegiMasterRow)l_lisServiceMasterRowList.get(i));
                }
            }
            else
            {
                l_serviceMasters = new WEB3SrvRegiServiceMaster[0];
            }

        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        //3) 2)�ō쐬�����T�[�r�X�}�X�^�[�N���X�̔z���ԋp����B
        return l_serviceMasters;
    }

    /**
     * (get�T�[�r�X���I���)<BR>
     * �w���������T�[�r�X�ɕR�t�����I������肵�A<BR>
     * �������Ƀ��[���l�Ŏw�肳�ꂽ�T�[�r�X���I���I�u�W�F�N�g��ԋp����B<BR>
     * (�w�肳�ꂽ���I��񂪎擾�ł��Ȃ������ꍇ�Anull��ԋp����B)<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���I���e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�\�����ԁi���j������.�w����� and<BR>
     * �@@�\�����ԁi���j������.�w�����<BR>
     * <BR>
     * 2) ���ݐ\�����Ԓ��̃T�[�r�X���I���̎擾<BR>
     * �@@����.���[���l=0�̏ꍇ<BR>
     * �@@���肵���T�[�r�X���I���Params�������ɁA�T�[�r�X���I����<BR>
     * �@@�R���X�g���N�^���R�[�����A���������C���X�^���X��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���I���Row=���肵���T�[�r�X���I���Params<BR>
     * <BR>
     * 3) ���񕪂̃T�[�r�X���I���̎擾�i����.���[���l��0�̏ꍇ�j<BR>
     * �@@�ȉ��̏����Łu�T�[�r�X���I���e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�\�����ԁi���j������.�w�����<BR>
     * [���я�]<BR>
     * �@@�\�����ԁi���j ����<BR>
     * <BR>
     *  3-1) ��������[(����.���[���l-1)]�̗v�f���擾����B<BR>
     * <BR>
     * 4) �O�񕪂̃T�[�r�X���I���̎擾�i����.���[���l��0�̏ꍇ�j<BR>
     * �@@�ȉ��̏����Łu�T�[�r�X���I���e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�\�����ԁi���j������.�w�����<BR>
     * [���я�]<BR>
     * �@@�\�����ԁi���j �~��<BR>
     * <BR>
     *  4-1) ��������[(����.���[���l�̐�Βl-1)]�̗v�f���擾����B<BR>
     * <BR>
     * 5) �擾�����������ʂ������ɁA�T�[�r�X���I���̃R���X�g���N�^��<BR>
     * �@@�@@�R�[�����A���������C���X�^���X��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���I���Row=�擾�����T�[�r�X���I���Params<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_tsDesignateDateTime - (�w�����)<BR>
     * @@param l_intRollValue - (���[���l)<BR>
     * <BR>
     * EX)<BR>
     * 0�c�\�����ԓ��ɁA�w�肳�ꂽ�\���������݂��钊�I���<BR>
     * 1�c�\�����ԓ��ɁA�w�肳�ꂽ�\���������݂��钊�I���̎��̒��I���<BR>
     * -1�c�\�����ԓ��ɁA�w�肳�ꂽ�\���������݂��钊�I���̑O�̒��I���<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo
     * @@roseuid 4106232A023B
     */
    public WEB3SrvRegiServiceLotInfo getSrvLotInfo(String l_strInstitutionCode, String l_strSrvDiv, Timestamp l_tsDesignateDateTime, int l_intRollValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvLotInfo(String, String, Timestamp, int) ";
        log.entering(STR_METHOD_NAME);

        if (l_tsDesignateDateTime == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3SrvRegiServiceLotInfo l_serviceLotInfo = null;

        try
        {
            //2) ���ݐ\�����Ԓ��̃T�[�r�X���I���̎擾����.���[���l=0�̏ꍇ
            if (l_intRollValue == 0)
            {
                //1) �ȉ��̏����Łu�T�[�r�X���I���e�[�u���v����������B
                String l_strWhere = " institution_code = ? and srv_div = ? " +
                    " and appli_date_from <= ? and appli_date_to >= ? ";

                Object[] l_obj = {l_strInstitutionCode,
                    l_strSrvDiv,
                    l_tsDesignateDateTime,
                    l_tsDesignateDateTime};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

                List l_lisServiceLotInfoRowList =
                    l_queryProcessor.doFindAllQuery(SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException

                if (l_lisServiceLotInfoRowList == null || l_lisServiceLotInfoRowList.size() == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                l_serviceLotInfo =
                    new WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisServiceLotInfoRowList.get(0));
            }
            //3) ���񕪂̃T�[�r�X���I���̎擾�i����.���[���l��0�̏ꍇ�j
            else if (l_intRollValue > 0)
            {
                String l_strWhere =
                    " institution_code = ? and srv_div = ? and appli_date_from >= ? ";

                Object[] l_obj = {l_strInstitutionCode,
                    l_strSrvDiv,
                    l_tsDesignateDateTime};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

                List l_lisServiceLotInfoRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiLotInfoRow.TYPE,
                    l_strWhere,
                    " appli_date_from asc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException


                if (l_lisServiceLotInfoRowList == null || l_lisServiceLotInfoRowList.size() < l_intRollValue)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //5) �擾�����������ʂ������ɁA�T�[�r�X���I���̃R���X�g���N�^��
                l_serviceLotInfo =
                    new WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisServiceLotInfoRowList.get(l_intRollValue - 1));
            }
            //according to the QA of WEB3-SEVREGI-1-CD-0020
            //4) �O�񕪂̃T�[�r�X���I���̎擾�i����.���[���l��0�̏ꍇ�j
            else
            {
                String l_strWhere =
                    " institution_code = ? and srv_div = ? and appli_date_to <= ? ";

                Object[] l_obj = {
                    l_strInstitutionCode,
                    l_strSrvDiv,
                    l_tsDesignateDateTime};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

                List l_lisServiceLotInfoRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiLotInfoRow.TYPE,
                    l_strWhere,
                    " appli_date_from desc ",
                    "",
                    l_obj);//DataNetworkException, DataQueryException

                if (l_lisServiceLotInfoRowList == null || l_lisServiceLotInfoRowList.size() < Math.abs(l_intRollValue))
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //5) �擾�����������ʂ������ɁA�T�[�r�X���I���̃R���X�g���N�^��
                l_serviceLotInfo = new WEB3SrvRegiServiceLotInfo(
                    (SrvRegiLotInfoRow)l_lisServiceLotInfoRowList.get(Math.abs(l_intRollValue) - 1));
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
         }
         catch (DataQueryException l_ex)
         {
             //DB�A�N�Z�X�����s�̏ꍇ
             log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

             log.exiting(STR_METHOD_NAME);

             //��O���X���[����
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
         }

         log.exiting(STR_METHOD_NAME);

         return l_serviceLotInfo;
    }

    /**
     * (get�����T�[�r�X���I���ꗗ)<BR>
     * ���T�[�r�X�̃T�[�r�X���I���̂����A���I�������ݓ��t�ȑO�̔z���Ԃ��B<BR>
     * <BR>
     * 1) �T�[�r�X���I���e�[�u�����������A�T�[�r�X���I���Params��<BR>
     * List���擾����B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=this.�T�[�r�X�敪 and<BR>
     * �@@(���I�������ݓ��t<BR>
     *   or<BR>
     *   (���I��=null and �\������(��)�����ݓ���))<BR>
     * [���ёւ�]<BR>
     * �@@�\�����ԁi���j�ŏ���<BR>
     * <BR>
     * 2) �擾����List����T�[�r�X���I���Params���Ƃ肾���A�T�[�r�X<BR>
     * ���I���I�u�W�F�N�g��<BR>
     * �@@��������B���������T�[�r�X���I���I�u�W�F�N�g��z��ɐݒ肵�ĕԂ��B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���I���Row=�擾�����T�[�r�X���I���Params<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo[ ]
     * @@roseuid 412EED890065
     */
    public WEB3SrvRegiServiceLotInfo[] getActionSrvLotInfoList(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getActionSrvLotInfoList(String, String) ";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceLotInfo[] l_srvLotInfos = null;

        try
        {
        	//��Q�Ή� NO_2040
            //1) �T�[�r�X���I���e�[�u�����������A�T�[�r�X���I���Params��
            String l_strWhere =
                " institution_code = ? and srv_div = ? and (lot_date <= ? or (lot_date is null and appli_date_to < ?))";

			//��Q�Ή�  NO_U01711
            //���ݓ����̎擾
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
			log.debug("�yl_datSystemDate�i���t�j�z : " + l_datSystemDate);
			
			Date l_datSystemDateTime = new Date(l_tsSystemTimestamp.getTime());
			log.debug("�yl_datSystemDateTime�i�����j�z : " + l_datSystemDateTime);

            Object[] l_obj = {l_strInstitutionCode, l_strSrvDiv, l_datSystemDate, l_datSystemDateTime};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE,
                l_strWhere,
                " appli_date_from asc ",
                "",
                l_obj);//DataNetworkException, DataQueryException

            if (l_lisSrvLotInfoRowList != null)
            {
                int l_intSrvLotInfoRowCnt = l_lisSrvLotInfoRowList.size();

                //2) �擾����List����T�[�r�X���I���Params���Ƃ肾���A�T�[�r�X
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[l_intSrvLotInfoRowCnt];

                for (int i = 0; i < l_intSrvLotInfoRowCnt; i++)
                {
                    l_srvLotInfos[i] =  new WEB3SrvRegiServiceLotInfo(
                        (SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(i));
                }
            }
            else
            {
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[0];
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_srvLotInfos;
    }

    /**
     * (is�ڋq�\���\)<BR>
     * ���Y�T�[�r�X���ڋq�ɂƂ��Đ\���\�ȃT�[�r�X���ǂ����𔻒肷��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���Ǘ��jis�ڋq�\���\�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_srvMaster - (�T�[�r�X�}�X�^�[)<BR>
     * �T�[�r�X�}�X�^�[�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@roseuid 411A04800396
     */
    public boolean isAccountAppliPossible(WEB3GentradeSubAccount l_subAccount, WEB3SrvRegiServiceMaster l_srvMaster) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " isAccountAppliPossible(WEB3GentradeSubAccount, WEB3SrvRegiServiceMaster)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_srvMaster == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1:<���ݓ��t�̎擾>
        //U00838
        Timestamp l_tsNowDateTmp = GtlUtils.getTradingSystem().getSystemTimestamp();
        Date l_tsNowDate = WEB3DateUtility.toDay(l_tsNowDateTmp);

        //1.2:is�\���\()
        boolean l_blnAppliPossible = l_srvMaster.isAppliPossible();

        //1.3:<���򏈗� *1>
        //1.3.1:false
        if (!l_blnAppliPossible)
        {
            log.debug("<���򏈗� *1>");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //according to the QA of WEB3-SRVREGI-A-CD-0027
        //1.4:get�\���v�T�[�r�X(is�s���b�N : boolean)
        WEB3SrvRegiApplicationRequiredService l_applicationRequiredService =
            l_srvMaster.getAppliRequiredSrv(false);

        if (l_applicationRequiredService == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.4.1:get���I�ݒ�()
        String l_strLotDiv = l_applicationRequiredService.getLotDiv();

        //1.5:<���򏈗� *2>
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            
			//��Q�Ή��@@NO_U01563 
			//�\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j�@@�� ���ݓ��t(*) �� 
			//�\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j�v 
			if(l_applicationRequiredService.getAppliDateFrom() != null &&
				l_applicationRequiredService.getAppliDateTo() != null){
								
				int l_appliFrom = l_applicationRequiredService.getAppliDateFrom().intValue();
				int l_appliTo = l_applicationRequiredService.getAppliDateTo().intValue();
				
				Calendar l_cal = new GregorianCalendar();
				l_cal.setTime(l_tsNowDateTmp);
				int l_day = l_cal.get(Calendar.DAY_OF_MONTH);
				
				//�\���\���ԁ@@(��)��(��)			
				if(l_appliFrom <= l_appliTo){
					if(!(l_appliFrom <= l_day) || !(l_day <= l_appliTo)){
						return false;
					}
				}
				else
				{
				//�\���\���ԁ@@(��)��(��)
					if( !(l_appliFrom <= l_day && l_day <= 31) || !(1 <= l_day && l_day <= l_appliTo)){
						return false;
					}
				}
			}
        }

        //1.6:<���򏈗� *4>
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("<���򏈗� *4>");
            //1.6.1:get�T�[�r�X���I���(String, String, Timestamp, int)
            if (this.getSrvLotInfo(l_srvMaster.getInstitutionCode(),
                l_srvMaster.getSrvDiv(),
                GtlUtils.getTradingSystem().getSystemTimestamp(),0) == null)
            {
                log.debug("getSrvLotInfo = null");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //1.7:get�T�[�r�X�\���o�^(�،���ЃR�[�h : String, ���X�R�[�h : String, �T�[�r�X�敪
        //: String, �����R�[�h : String, ����敪 : String, �L���敪 : String, is�s���b�N : boolean)
        WEB3SrvRegiRegistService l_registSrv =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3GentradeSrvRegiApplication l_registSrvAppli = l_registSrv.getServiceRegist(
            l_srvMaster.getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_srvMaster.getSrvDiv(),
            l_subAccount.getMainAccount().getAccountCode(),
            WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
            WEB3EffectiveDivDef.EFFECTIVE,
            false);

        //1.8:<���򏈗� *5>
        if (l_registSrvAppli != null)
        {
            log.debug("<���򏈗� *5>");
            //1.8.1:get�\�����I�敪( )
            //String l_strAppliLotDiv = l_registSrvAppli.getAppliLotDiv();

            //1.8.2:<���򏈗� *6>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug("<���򏈗� *6>");
                Timestamp l_tsAppliEndDate = l_registSrvAppli.getAppliEndDate();

                Calendar l_cal = new GregorianCalendar();
                l_cal.setTime(l_tsAppliEndDate);
                l_cal.add(Calendar.MONTH, -1);

                l_tsAppliEndDate = new Timestamp(l_cal.getTime().getTime());

                if (((WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_registSrvAppli.getAppliLotDiv()) &&
                WEB3DateUtility.compareToDay(l_tsNowDate,l_tsAppliEndDate) < 0)) &&
                (!WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_registSrvAppli.getAppliLotDiv())))
                {
                    log.debug("<���򏈗� *6>return false");
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        //1.9:<�e�`�F�b�N��false���ԋp����Ȃ������ꍇ>
        //1.9.1:true
        return true;
    }

    /**
     * (is���p�\���\)<BR>
     * ���Y�T�[�r�X���ڋq�ɂƂ��Ď��p�\���\�ȃT�[�r�X���ǂ����𔻒肷��B<BR>
     * <BR>
     * 1) �T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[( ).get�\���v<BR>
     * �T�[�r�X( ).get���I�ݒ�( )<BR>
     * �@@���R�[�����A�擾�������I�ݒ肪"��"�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * [get�T�[�r�X�}�X�^�[�ɓn������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@is�s���b�N=false<BR>
     * <BR>
     * [get�\���v�T�[�r�X�ɓn������]<BR>
     * �@@is�s���b�N=false<BR>
     * <BR>
     * 2) �T�[�r�X���p�\���o�^�T�[�r�X.get�����\���敪( )���R�[������B<BR>
     * <BR>
     * [get�����\���敪�ɓn������]<BR>
�@@   * �،���ЃR�[�h=����.�،���ЃR�[�h<BR>
�@@   * ���X�R�[�h=����.���X�R�[�h<BR>
�@@   * �T�[�r�X�敪=����.�T�[�r�X�敪<BR>
�@@   * �����R�[�h=����.�����R�[�h<BR>
     * <BR>
     * 3) �ԋp�l�̐ݒ�<BR>
     *  3-1) 2)�̖߂�l="��"�̏ꍇ�Atrue��ԋp����B<BR>
     *  3-2) 2)�̖߂�l="�L"�̏ꍇ�Afalse��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@return boolean
     * @@roseuid 411C133903D4
     */
    public boolean isTrialAppliPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isTrialAppliPossible(String, String, String, String) ";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsTrialAppliPossible = false;
        //according to the QA of WEB3-SEVREGI-1-CD-0021
            //1) �T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[().get�\���v�T�[�r�X().get���I�ݒ�()
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(
            this.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false).getAppliRequiredSrv(false).getLotDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //2) �T�[�r�X���p�\���o�^�T�[�r�X.get�����\���敪( )���R�[������B
        WEB3SrvRegiRegistService l_service =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

        String l_strResult = l_service.getInitializeAppliDiv(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv,l_strAccountCode);

        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strResult))
        {
            l_blnIsTrialAppliPossible = true;
        }
        else
        {
            l_blnIsTrialAppliPossible = false;
        }

        log.exiting(STR_METHOD_NAME);

        //3) �ԋp�l�̐ݒ�
        return l_blnIsTrialAppliPossible;
    }

    /**
     * (is�萔������)<BR>
     * �ڋq�̑O�����̎萔���݌v�z�ƌ��݂̐\���󋵂���A<BR>
     * ���Y�ڋq���萔���������N���A���Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���Ǘ��jis�萔�������v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_srvServiceMaster - (�T�[�r�X�}�X�^�[)<BR>
     * @@return boolean
     */
    public boolean isCommCond(WEB3GentradeSubAccount l_subAccount, WEB3SrvRegiServiceMaster l_srvServiceMaster) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCommCond(WEB3GentradeSubAccount, WEB3SrvRegiServiceMaster)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_srvServiceMaster == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //get�\���v�T�[�r�X(boolean)
            WEB3SrvRegiApplicationRequiredService l_AppliRequiredSrv =
                l_srvServiceMaster.getAppliRequiredSrv(false);

            if (l_AppliRequiredSrv == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get��萔�����v�z( )
            double l_dblAmount2 = Double.parseDouble(l_AppliRequiredSrv.getMinCommAmt());

            //get�񋟌`��( )
            //get�񋟌`��()�� 0 or 1 �ȊO�̏ꍇ�Afalse��ԋp����
            String l_strSuppltDiv = l_AppliRequiredSrv.getSupplyDiv();
            if (!(WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strSuppltDiv)
                || WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY.equals(l_strSuppltDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

        	//��Q�Ή�  NO_U01711
            //1.1:�����ݔN���̎擾��
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);

            //1.2:get�萔�������ꗗ( )
            List l_lisCommCondList = l_srvServiceMaster.getCommCondList();

            if (l_lisCommCondList == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            int l_intCommCondCnt = l_lisCommCondList.size();

            if (l_intCommCondCnt == 0)
            {
                 log.debug("get�萔�������ꗗ()=0");

                 log.exiting(STR_METHOD_NAME);
                 return false;
            }

            //1.3:getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            //1.4:�������萔���݌v�e�[�u������������
            String l_strWhere =
                " institution_code = ? and branch_code = ? and account_code = ? and accumulate_term = ? and (order_acc_product = ?";

            Calendar l_cal = new GregorianCalendar();
			l_cal.setTime(l_datSystemDate );
            l_cal.add(Calendar.MONTH, -1);

            Date l_dat = l_cal.getTime();

            String l_strYearMonth = WEB3DateUtility.formatDate(l_dat, "yyyyMM");

            Object[] obj = new Object[4 + l_intCommCondCnt];
            obj[0] = l_srvServiceMaster.getInstitutionCode();
            obj[1] = l_subAccount.getMainAccount().getBranch().getBranchCode();
            obj[2] = l_subAccount.getMainAccount().getAccountCode();
            obj[3] = l_strYearMonth;

            for (int i = 0; i < l_intCommCondCnt; i++)
            {
                if (i > 0)
                {
                    l_strWhere += " or order_acc_product = ?";
                }

                SrvRegiCommCondRow l_srvRegiCommCondRow = (SrvRegiCommCondRow)l_lisCommCondList.get(i);
                obj[4 + i] = l_srvRegiCommCondRow.getOrderAccProduct();
            }
            l_strWhere += ") ";

            log.debug("l_strWhere:" + l_strWhere);

            log.debug("l_strYearMonth:" + l_strYearMonth + ":" + l_dat.toString());

            List l_lisSrvRegiDealingCommRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiDealingCommRow.TYPE, l_strWhere, obj);

            //1.5:���������ʁ�0���̏ꍇ�Afalse��ԋp���遄
            if (l_lisSrvRegiDealingCommRowList == null || l_lisSrvRegiDealingCommRowList.size() == 0)
            {
                log.debug("1.5:���������ʁ�0���̏ꍇ�Afalse��ԋp���遄");
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            int l_intSrvRegiDealingCommRowLisRowCnt = l_lisSrvRegiDealingCommRowList.size();
            //1.6:���萔���݌v�z���Z��
            double l_dblAmount = 0;
            for (int i = 0; i < l_intSrvRegiDealingCommRowLisRowCnt; i++)
            {
                SrvRegiDealingCommRow l_srvRegiDealingCommRow =
                    (SrvRegiDealingCommRow)l_lisSrvRegiDealingCommRowList.get(i);
                l_dblAmount += l_srvRegiDealingCommRow.getCommAmt();
            }

            log.debug("���萔���݌v�z���Z��:" + l_dblAmount);

            //1.8:��get��萔�����v�z���Z�o�����萔���݌v�z�̏ꍇ��
            if (l_dblAmount2 > l_dblAmount)
            {
                log.debug("get��萔�����v�z���Z�o�����萔���݌v�z�̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        //1.9:true
        return true;
    }

    /**
     * (get�萔�������ꗗ)<BR>
     * �w�肳�ꂽ�T�[�r�X�ɕR�t���T�[�r�X���p�萔�������}�X�^�[��<BR>
     * �ꗗ���擾���A�ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̌��������Łu�T�[�r�X���p�萔�������}�X�^�[�e�[�u���v����������B<BR>
     *  [��������] <BR>
     * �،���ЃR�[�h=����.�،���ЃR�[�h <BR>
     * <BR>
     * 2) �������ʂ��u�T�[�r�X���p�萔�������}�X�^�[�v�I�u�W�F�N�g<BR>
     * �̃��X�g�Ƃ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@return List
     */
    public List getCommCondList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommCondList(String)";
        log.entering(STR_METHOD_NAME);

        List l_lisCommCondMasters = null;

        try
        {
            //1) �ȉ��̌��������Łu�T�[�r�X���p�萔�������}�X�^�[�e�[�u���v����������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvRegiCommCondRows = l_queryProcessor.doFindAllQuery(
                SrvRegiCommCondMasterRow.TYPE,
                " institution_code = ? ",
                new Object[]{l_strInstitutionCode});//DataNetworkException, DataQueryException

            l_lisCommCondMasters = new ArrayList();

            if (l_lisSrvRegiCommCondRows != null)
            {
                //2) �������ʂ��u�T�[�r�X���p�萔�������}�X�^�[�v�I�u�W�F�N�g�̃��X�g�Ƃ��ĕԋp����B
                int l_intSrvRegiCommCondRowCnt = l_lisSrvRegiCommCondRows.size();

                for (int i = 0; i < l_intSrvRegiCommCondRowCnt; i++)
                {
                    SrvRegiCommCondMasterRow l_srvRegiCommCondMasterRow = (SrvRegiCommCondMasterRow)l_lisSrvRegiCommCondRows.get(i);

                    WEB3SrvRegiCommCondMaster l_commCondMaster =
                        new  WEB3SrvRegiCommCondMaster(
                            l_strInstitutionCode,
                            l_srvRegiCommCondMasterRow.getOrderAccProduct(),
                            l_srvRegiCommCondMasterRow.getCommProdTypeName());

                    l_lisCommCondMasters.add(l_commCondMaster);
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_lisCommCondMasters;
    }

    /**
     * (validate�\������)<BR>
     * �w�肳�ꂽ�\�����Ԃ̑Ó������`�F�b�N����B<BR>
     * <BR>
     * 1) �ȉ��������ɁA�u�T�[�r�X���I���e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�K�p�I���������ݓ��t(*1) and<BR>
     *   �ʔ�!=����.���I���ID and<BR>
     *  ((�\�����ԁi���j������.�\���J�n�� and �\�����ԁi���j������.�\���I����) or<BR>
     * �@@(�\�����ԁi���j������.�\���J�n�� and �\�����ԁi���j������.�\���I����) or<BR>
     * �@@(�\�����ԁi���j������.�\���J�n�� and �\�����ԁi���j������.�\���I����))<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) �������ʁ�0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00983<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_lngLotteryId - (���I���ID)<BR>
     * @@param l_tsAppliStartDate - (�\���J�n��)<BR>
     * @@param l_tsAppliEndDate - (�\���I����)<BR>
     * @@roseuid 41206EDF0358
     */
    public void validateAppliDate(String l_strInstitutionCode, String l_strSrvDiv, Long l_lngLotteryId, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliDate(String, String, Timestamp, Timestamp) ";
        log.entering(STR_METHOD_NAME);

        if (l_tsAppliStartDate == null || l_tsAppliEndDate == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1) �ȉ��������ɁA�u�T�[�r�X���I���e�[�u���v����������B
			String l_strWhere = null;
			Object[] l_obj = null;
            if (l_lngLotteryId != null)
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"consecutive_numbers != ? and " +
					"((appli_date_from >= ? and appli_date_from <= ?) or " +
					"(appli_date_to >= ? and appli_date_to <= ?) or " +
					"(appli_date_from <= ? and appli_date_to >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[10];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_lngLotteryId;
				l_obj[4] = l_tsAppliStartDate;
				l_obj[5] = l_tsAppliEndDate;
				l_obj[6] = l_tsAppliStartDate;
				l_obj[7] = l_tsAppliEndDate;
				l_obj[8] = l_tsAppliStartDate;
				l_obj[9] = l_tsAppliEndDate;	
            }
            else
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"((appli_date_from >= ? and appli_date_from <= ?) or " +
					"(appli_date_to >= ? and appli_date_to <= ?) or " +
					"(appli_date_from <= ? and appli_date_to >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[9];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_tsAppliStartDate;
				l_obj[4] = l_tsAppliEndDate;
				l_obj[5] = l_tsAppliStartDate;
				l_obj[6] = l_tsAppliEndDate;
				l_obj[7] = l_tsAppliStartDate;
				l_obj[8] = l_tsAppliEndDate;	
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException

            //2) �������ʁ�0���̏ꍇ�A��O���X���[����B
            if ( l_lisSrvLotInfoRowList != null && l_lisSrvLotInfoRowList.size() > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00983,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�K�p����)<BR>
     * �w�肳�ꂽ�K�p���Ԃ̑Ó������`�F�b�N����B<BR>
     * <BR>
     * 1) �ȉ��������ɁA�u�T�[�r�X���I���e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�K�p�I���������ݓ��t(*1)�̓��t���� and<BR>
     *   �ʔ�!=����.���I���ID and<BR>
     *  ((�K�p�J�n��������.�K�p�J�n�� and �K�p�J�n��������.�K�p�I����) or<BR>
     * �@@(�K�p�I����������.�K�p�J�n�� and �K�p�I����������.�K�p�I����) or<BR>
     * �@@(�K�p�J�n��������.�K�p�J�n�� and �K�p�I����������.�K�p�I����))<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) �������ʁ�0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00946<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_lngLotteryId - (���I���ID)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@roseuid 41206EDF0368
     */
    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, Long l_lngLotteryId, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliPeriod(String, String, Timestamp, Timestamp) ";
        log.entering(STR_METHOD_NAME);

        if (l_tsAppliStartDate == null || l_tsAppliEndDate == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1) �ȉ��������ɁA�u�T�[�r�X���I���e�[�u���v����������B
			String l_strWhere = null;
			Object[] l_obj = null;
            if (l_lngLotteryId != null)
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"consecutive_numbers != ? and " +
					"((appli_start_date >= ? and appli_start_date <= ?) or " +
					"(appli_end_date >= ? and appli_end_date <= ?) or " +
					"(appli_start_date <= ? and appli_end_date >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[10];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_lngLotteryId;
				l_obj[4] = l_tsAppliStartDate;
				l_obj[5] = l_tsAppliEndDate;
				l_obj[6] = l_tsAppliStartDate;
				l_obj[7] = l_tsAppliEndDate;
				l_obj[8] = l_tsAppliStartDate;
				l_obj[9] = l_tsAppliEndDate;
            }
            else
            {
				l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? and " +
					"((appli_start_date >= ? and appli_start_date <= ?) or " +
					"(appli_end_date >= ? and appli_end_date <= ?) or " +
					"(appli_start_date <= ? and appli_end_date >= ?))";

				Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

				l_obj = new Object[9];
				
				l_obj[0] = l_strInstitutionCode;
				l_obj[1] = l_strSrvDiv;
				l_obj[2] = l_tsNowDate;
				l_obj[3] = l_tsAppliStartDate;
				l_obj[4] = l_tsAppliEndDate;
				l_obj[5] = l_tsAppliStartDate;
				l_obj[6] = l_tsAppliEndDate;
				l_obj[7] = l_tsAppliStartDate;
				l_obj[8] = l_tsAppliEndDate;
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            List l_lisSrvLotInfoRowList =
                l_queryProcessor.doFindAllQuery(SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
            
            //2) �������ʁ�0���̏ꍇ�A��O���X���[����B
            if (l_lisSrvLotInfoRowList != null && l_lisSrvLotInfoRowList.size() > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00946,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME, l_ex);

            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceInfoManagement.class.getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�d�q������)<BR>
     * �ڋq���d�q���ɓ��ӂ��Ă��邩���肷��B<BR> 
     * <BR>
     * �P�D�\���v�T�[�r�X.is�d�q�������ݒ�()���R�[�����āA<BR> 
     * �d�q�����ӂ������Ƃ��Đݒ肳��Ă��邩�`�F�b�N����B<BR> 
     * �Q�Dis�d�q�������ݒ�()==true�̏ꍇ�A�d�q���V�X�e���ڑ��T�[�r�X.validate�d�q�����{()<BR> 
     * ���R�[�����A�d�q�����Ӄ`�F�b�N�����{����B<BR> 
     * <BR>
�@@   * [����] <BR>
�@@   * �@@�\�敪�F00�i�d�q�������`�F�b�N�j<BR>
     * <BR>
     * �R�Dvalidate�d�q�����{()�̖߂�l���A�u�����ӌڋq�v�������ꍇ�́A��O���X���[����B<BR> 
     * �i�d�q�����o�^�G���[�j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01965<BR>
     * <BR>
     * <BR>
     * ���d�q���V�X�e���ڑ��T�[�r�X�́A����.���V�X�e���C���^�t�F�[�X�̃T�[�r�X<BR>
     * @@param l_srvMaster - (�T�[�r�X�}�X�^�[)<BR>
     */
    public void validateBatoAgreement(WEB3SrvRegiServiceMaster l_srvMaster) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBatoAgreement(WEB3SrvRegiServiceMaster) ";
        log.entering(STR_METHOD_NAME);
        
        //WEB3-SRVREGI-A-CD-0139
        WEB3SrvRegiApplicationRequiredService l_requiredSrv = l_srvMaster.getAppliRequiredSrv(false);
        
        boolean l_blnIsElectricIssue = l_requiredSrv.isElectricIssue();
        
        if (l_blnIsElectricIssue)
        {
            WEB3GentradeBatoClientService l_batoClientService = 
                (WEB3GentradeBatoClientService) Services.getService(WEB3GentradeBatoClientService.class);
            String l_strResult =l_batoClientService.validateBato(
                WEB3GentradeBatoFunctionDivDef.BATO_SERVICE_REG_SERVICE);
                
            if (WEB3GentradeBatoTranHistServiceResultDef.NOT_AGREEMENT.equals(l_strResult))
            {
                String l_srMessage = "�߂�l�u" + l_strResult + "�v�͖����ӌڋq�ł�.";
                log.debug(l_srMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01965,
                    getClass().getName() + STR_METHOD_NAME,
                    l_srMessage);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�T�[�r�X�\���������)<BR>
     * �T�[�r�X�\�������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j �T�[�r�X�\�������e�[�u�����f�[�^���擾����B<BR>
     * <BR>
     * �@@�P�j Object�z��𐶐����A�ȉ���v�f�ɐݒ�<BR>
     * �@@�@@�@@�@@Object[0]�i�����j�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@Object[1]�i�����j���X�R�[�h<BR>
     * �@@�@@�@@�@@Object[2]�i�����j�����R�[�h + "%"<BR>
     * �@@�@@�@@�@@Object[3]�i�����j�T�[�r�X�敪<BR>
     * �@@�@@�@@�@@Object[4]�i�����j�A�b�v���[�h�敪<BR>
     * <BR>
     * �@@�Q�j �T�[�r�X�\�������e�[�u���Ƀ��R�[�h�����݂��邩��������B<BR>
     * �@@�@@�@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@arg0�F  �T�[�r�X�\�������e�[�u��RowType<BR>
     * �@@�@@�@@�@@�@@arg1�F  "institution_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and branch_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and account_code like ?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and srv_div=?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(����.�A�b�v���[�h�敪() == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̏������ǉ�����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"and proc_div == '0' or null <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and (appli_start_date <= ���ݓ��t(YYYYMMDD) <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or appli_start_date == null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and (appli_end_date >= ���ݓ��t(YYYYMMDD) <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or appli_end_date == null)"<BR>
     * �@@�@@�@@�@@�@@arg2�F  �P�j�ō쐬����Object�z��<BR>
     * <BR>
     * �@@�R�j �Q�j�̖߂�lList�̒��� > 0 �̏ꍇ�A�Q�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * �@@�S�j �Q�j�̖߂�lList�̒��� = 0 �̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strUploadDiv - (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getServiceAppliAttributeInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strSrvDiv,
        String l_strUploadDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServiceAppliAttributeInfo(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �T�[�r�X�\�������e�[�u�����f�[�^���擾����B
        //�P�j Object�z��𐶐����A�ȉ���v�f�ɐݒ�
        //      �P�j Object�z��𐶐����A�ȉ���v�f�ɐݒ�
        //      Object[0]�i�����j�،���ЃR�[�h
        //      Object[1]�i�����j���X�R�[�h
        //      Object[2]�i�����j�����R�[�h + "%"
        //      Object[3]�i�����j�T�[�r�X�敪
        //      Object[4]�i�����j�A�b�v���[�h�敪
        List l_lisWheres = new ArrayList();
        l_lisWheres.add(l_strInstitutionCode);
        l_lisWheres.add(l_strBranchCode);
        l_lisWheres.add(l_strAccountCode + "%");
        l_lisWheres.add(l_strSrvDiv);

        //arg1�F  "institution_code=?
        //         and branch_code=?
        //         and account_code like ?
        //         and srv_div=?"
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and�@@branch_code = ? ");
        l_sbWhere.append(" and�@@account_code like ? ");
        l_sbWhere.append(" and�@@srv_div = ? ");

        //         (����.�A�b�v���[�h�敪() == null �̏ꍇ�A�ȉ��̏������ǉ�����)
        //        "and proc_div == '0' or null
        //         and (appli_start_date <= ���ݓ��t(YYYYMMDD) or appli_start_date == null)
        //         and (appli_end_date >= ���ݓ��t(YYYYMMDD) or appli_end_date == null)"
        if (l_strUploadDiv == null)
        {
            l_lisWheres.add(WEB3SrvAppliAttributeProcDivDef.NOT_PROCESSED);
            l_lisWheres.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            l_lisWheres.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            l_sbWhere.append(" and (proc_div = ? ");
            l_sbWhere.append(" or proc_div is null) ");
            l_sbWhere.append(" and (appli_start_date <= ? ");
            l_sbWhere.append(" or appli_start_date is null) ");
            l_sbWhere.append(" and (appli_end_date >= ? ");
            l_sbWhere.append(" or appli_end_date is null) ");
        }

        Object[] l_objWheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWheres);

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcesser.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (validate����\��)<BR>
     * ���Y�T�[�r�X���O���A�g�T�[�r�X�A���ڋq���V�K�ڋq�̏ꍇ�A<BR>
     * �O���A�g���Ǘ��e�[�u���ɖ��g�p�̃��R�[�h�����邩�`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���Ǘ��jvalidate����\����Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} �i�T�[�r�X���Ǘ��jvalidate����\��: <BR>
     * �@@�@@�@@�@@�@@1.1.2.1get�O���A�g���( ) �̖߂�l�� null �ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03027<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} �i�T�[�r�X���Ǘ��jvalidate����\��: <BR>
     * �@@�@@�@@�@@�@@1.1.3.1get�O���A�g���()�̖߂�l�� null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_srvRegiServiceMaster - (�T�[�r�X�}�X�^�[)<BR>
     * �T�[�r�X�}�X�^�[<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_blnNewApplyDiv - (�V�K�\���敪)<BR>
     * �V�K�\���敪<BR>
     * �V�K�\���Ftrue  �p���\���Ffalse<BR>
     * @@throws WEB3BaseException
     */
    public void validateSpecialApply(
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        boolean l_blnNewApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSpecialApply(WEB3SrvRegiServiceMaster, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_srvRegiServiceMaster == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����.�T�[�r�X�}�X�^.���ꏈ���敪 = 1�i�O���A�g�T�[�r�X�j �̏ꍇ
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();

        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            //get�O���A�g���(String, String, String, String, boolean)
            //[����]
            //�T�[�r�X�敪 = ����.�T�[�r�X�}�X�^.�T�[�r�X�敪
            //�،���ЃR�[�h = ����.�،���ЃR�[�h
            //���X�R�[�h = ����.���X�R�[�h
            //�����R�[�h = ����.�����R�[�h
            //is�s���b�N = false
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);

            WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
                l_srvRegiOtherOrgService.getOtherOrgInfo(
                    l_srvRegiServiceMaster.getSrvDiv(),
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    false);

            //����.�V�K�\���敪 = true �̏ꍇ
            if (l_blnNewApplyDiv)
            {
                //get�O���A�g���( ) �̖߂�l�� null �ȊO�̏ꍇ�A��O���X���[����B
                if (l_srvRegiOtherOrgInfoAdmin != null)
                {
                    log.debug("�O���A�g�����擾����͂����Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03027,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "�O���A�g�����擾����͂����Ȃ��B");
                }

                //get���g�p�ʔԏ��(String, boolean)
                //�T�[�r�X�敪 = ����.�T�[�r�X�}�X�^.�T�[�r�X�敪
                //is�s���b�N = false
                l_srvRegiOtherOrgService.getUnUseSequenceNumberInfo(
                    l_srvRegiServiceMaster.getSrvDiv(),
                    false);
            }
            else
            {
                //get�O���A�g���()�̖߂�l�� null �̏ꍇ�A��O���X���[����B
                if (l_srvRegiOtherOrgInfoAdmin == null)
                {
                    log.debug("�O���A�g�����擾�ł��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "�O���A�g�����擾�ł��܂���B");
                }
            }
        }
        log.debug("�T�[�r�X���Ǘ�#validate����\��pass");
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�V�K�\��)<BR>
     * �Y���ڋq���A�T�[�r�X��V�K�\����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���Ǘ��jis�V�K�\����Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isNewApply(
        WEB3GentradeSubAccount l_subAccount,
        String l_strServiceDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNewApply(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        //get�T�[�r�X�\���o�^(String, String, String, String, String, String, boolean)
        //�،���ЃR�[�h = ����.�⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
        //���X�R�[�h = ����.�⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
        //�T�[�r�X�敪=����.�T�[�r�X�敪
        //�����R�[�h = ����.�⏕�����I�u�W�F�N�g.getMainAccoutn( ).getAccountCode( )
        //����敪="�w�薳"
        //�L���敪="�L��"
        //is�s���b�N=false
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3GentradeSrvRegiApplication l_srvRegiApplication =
            l_srvRegiRegistService.getServiceRegist(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getWeb3GenBranch().getBranchCode(),
                l_strServiceDiv,
                l_subAccount.getMainAccount().getAccountCode(),
                null,
                WEB3EffectiveDivDef.EFFECTIVE,
                false);

        //get�T�[�r�X�\���o�^() �̖߂�l�� null �̏ꍇ
        if (l_srvRegiApplication == null)
        {
            log.debug("get�T�[�r�X�\���o�^() �̖߂�l�� null �̏ꍇ return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("get�T�[�r�X�\���o�^() �̖߂�l != null �̏ꍇ return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get���ꏈ���T�[�r�X�}�X�^�[�ꗗ)<BR>
     * �T�[�r�X�}�X�^�[�̈ꗗ�����o���A�ԋp����B<BR>
     *<BR>
     * 1) �ȉ��̏����Łu�T�[�r�X�}�X�^�[�e�[�u���v�����o����B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@and ���ꏈ���敪=����.���ꏈ���敪<BR>
     *<BR>
     * [���я�]<BR>
     * �@@�T�[�r�X�敪�@@����<BR>
     *<BR>
     * 2) �������ʂ̌������A�ȉ����J��Ԃ��B�i�擾�ł��Ȃ��ꍇ�́A��O���X���[����B �j<BR>
     *  2-1) �������ʂ̃T�[�r�X�}�X�^�[Params�������ɁA<BR>
     * �@@�@@�T�[�r�X�}�X�^�[�N���X�̃R���X�g���N�^���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�}�X�^�[Row: �擾�����T�[�r�X�}�X�^�[Params<BR>
     *<BR>
     *  2-2) ���������T�[�r�X�}�X�^�[�I�u�W�F�N�g��z��ɒǉ�����B<BR>
     *<BR>
     * 3) 2)�ō쐬�����T�[�r�X�}�X�^�[�N���X�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSpecialProcessDiv - (���ꏈ���敪)<BR>
     * ���ꏈ���敪<BR>
     * @@return WEB3SrvRegiServiceMaster[ ]
     */
    public WEB3SrvRegiServiceMaster[] getSpecialProcessSrvMasterList(
        String l_strInstitutionCode,
        String l_strSpecialProcessDiv)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSpecialProcessSrvMasterList(String, String) ";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceMaster[] l_serviceMasters = null;

        try
        {
            // 1) �ȉ��̏����Łu�T�[�r�X�}�X�^�[�e�[�u���v�����o����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // [��������]
            // �@@�،���ЃR�[�h=����.�،���ЃR�[�h
            // �@@and ���ꏈ���敪=����.���ꏈ���敪
            String l_strWhere = " institution_code = ? ";

            List l_lisServiceMasterRowList = null;

            Object[] l_obj;

            if (l_strSpecialProcessDiv == null)
            {
                l_strWhere += " and special_process_div is null ";
                l_obj = new Object[]{l_strInstitutionCode};
            }
            else
            {
                l_strWhere += " and special_process_div = ? ";
                l_obj = new Object[]{l_strInstitutionCode, l_strSpecialProcessDiv};
            }

            // [���я�]<BR>
            // �@@�T�[�r�X�敪�@@����
            String l_strOrderBy = " srv_div asc ";

            l_lisServiceMasterRowList =
                l_queryProcessor.doFindAllQuery(
                    SrvRegiMasterRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_obj);

            // 2) �������ʂ̌������A�ȉ����J��Ԃ��B
            // �擾�ł��Ȃ��ꍇ�́A��O���X���[����B�j
            if (!l_lisServiceMasterRowList.isEmpty())
            {

                // 2-1) �������ʂ̃T�[�r�X�}�X�^�[Params�������ɁA
                // �@@�T�[�r�X�}�X�^�[�N���X�̃R���X�g���N�^���R�[������B
                // [����]
                // �@@�T�[�r�X�}�X�^�[Row: �擾�����T�[�r�X�}�X�^�[Params
                int l_intServiceMasterRowCnt = l_lisServiceMasterRowList.size();

                l_serviceMasters = new WEB3SrvRegiServiceMaster[l_intServiceMasterRowCnt];

                for (int i = 0; i < l_intServiceMasterRowCnt; i++)
                {
                    // 2-2) ���������T�[�r�X�}�X�^�[�I�u�W�F�N�g��z��ɒǉ�����B
                    l_serviceMasters[i] =
                        new WEB3SrvRegiServiceMaster(
                            (SrvRegiMasterRow)l_lisServiceMasterRowList.get(i));
                }
            }
            else
            {
                String l_strErrorMessage =
                    "�T�[�r�X�}�X�^�[�f�[�^���擾�ł��܂���B";
                log.debug(l_strErrorMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�T�[�r�X�}�X�^�[�f�[�^���擾�ł��܂���B");
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�A�N�Z�X�G���[�B");
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�A�N�Z�X�G���[�B");
        }

        log.exiting(STR_METHOD_NAME);

        // 3) 2)�ō쐬�����T�[�r�X�}�X�^�[�N���X�̔z���ԋp����B
        return l_serviceMasters;
    }

}
@
