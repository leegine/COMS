head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.02.17.16;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5584d8aa9ab6999;
filename	WEB3FXConnCommonServiceImpl.java;

1.1
date	2011.03.16.02.36.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXConnCommonServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڑ�����Impl(WEB3FXConnCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �đo�g (���u) �V�K�쐬 ���f��1173,1180,1182,1188
Revision History : 2009/08/14 �đo�g (���u) ���f��1190
Revision History : 2009/09/16 �����F (���u) ���f��1204 1025 1219 1223 1224 1227 1232
Revision History : 2009/10/14 �����F (���u) ���f��1239
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.gentrade.data.SoapConnectPrefRpcDao;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ڑ�����mpl)<BR>
 * �ڑ�����Impl<BR>
 *
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3FXConnCommonServiceImpl implements WEB3FXConnCommonService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXConnCommonServiceImpl.class);

    /**
     * (send�O���ڑ��˗����b�Z�[�W)<BR>
     * �O���ڑ��˗����b�Z�[�W�̑��t���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usend�O���ڑ��˗����b�Z�[�W�v�Q��<BR>
     * <BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@param l_compFxConditionParams - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@return WEB3ExtConnection
     * @@throws WEB3BaseException
     */
    public WEB3ExtConnection sendExtConnAskingMessage(
        CompFxConditionParams l_compFxConditionParams,
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendExtConnAskingMessage(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);

        //get�O���ڑ��C���X�^���X(�O���ڑ��V�X�e���R�[�h : String)
        //[����]
        // �O���ڑ��V�X�e���R�[�h�F ��Е�FX�V�X�e������Params����擾�����O���ڑ��V�X�e���R�[�h
        //�����敪�F ����.GFT�˗��d������.�����敪
        WEB3ExtConnection l_extConnection =
            WEB3FXExtConnectionManager.getExtConnectionInstance(
                l_compFxConditionParams.getExtConnectSystemCode(), l_fXGftAskingTelegramUnit.gftOperationDiv);

        //�ȉ��̏����ŁA�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j���烌�R�[�h���擾
        //[����]
        //  ���XID = ����.GFT�˗��d�����ׂ����ЃR�[�h�ƈ���.��Е�FX�V�X�e������.���X�R�[�h�𗘗p���āA���XID���擾����
        //  �ڑ��敪 = ����.��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        SoapConnectPrefRpcRow l_soapConnectPrefRpcRow;
        try
        {
            BranchRow l_branchRow =
                BranchDao.findRowByInstitutionCodeBranchCode(
                    l_fXGftAskingTelegramUnit.institutionCode,
                    l_compFxConditionParams.getBranchCode());
            l_soapConnectPrefRpcRow =
                SoapConnectPrefRpcDao.findRowByPk(
                    l_branchRow.getBranchId(),
                    l_compFxConditionParams.getFxSystemCode());
        }
        catch (DataFindException l_ex)
        {
            //�����R�[�h���擾�ł��Ȃ������ꍇ�͗�O���X���[����B
            log.error("�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j�̃��R�[�h���擾�ł��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams(l_soapConnectPrefRpcRow);
        //setSOAP�ڑ��p�v���L�V(�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���jParams)
        this.setSOAPConnectionProxy(l_soapConnectPrefRpcParams);

        //sendMessage(Message, �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���jParams, �⏕����)
        //[����]
        //�d�����b�Z�[�W�F����.GFT�˗��d������
        //�O���V�X�e��SOAP�v���t�@@�����X�F1.3�j�Ŏ擾�����O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���jparams
        l_extConnection.sendMessage(
            l_fXGftAskingTelegramUnit,
            l_soapConnectPrefRpcParams);

        //�O���ڑ��C���X�^���X��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_extConnection;
    }

    /**
     * (SOAP�ڑ��p�v���L�V)<BR>
     * SOAP�ڑ��p�v���L�V�ݒ���s���B<BR>
     * <BR>
     * 1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g����<BR>
     * �@@�@@�������A�ڑ�������擾����B<BR>
     * �@@getEndpointName().split(arg0 : String)<BR>
     * �@@�@@[����]<BR>
     * �@@�@@arg0�F ";"<BR>
     * <BR>
     * 2�j�v���L�V�ݒ���s���B<BR>
     * �@@���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ��<BR>
     * �@@�@@���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ����<BR>
     * �@@�@@���L�̒ʂ�v���p�e�B�Z�b�g���s���B<BR>
     * �@@�@@�i1�jkey   : "https.proxyHost"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f<BR>
     * �@@�@@�i2�jkey   : "https.proxyPort"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f<BR>
     * �@@�@@������L�̏����ȊO����<BR>
     * �@@�@@���L�̒ʂ�v���p�e�B�Z�b�g���s���B<BR>
     * �@@�@@�i1�jkey   : "http.proxyHost"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f<BR>
     * �@@�@@�i2�jkey   : "http.proxyPort"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f<BR>
     * �@@���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��<BR>
     * �@@�@@�����Ȃ�<BR>
     * �@@����L�̏����ȊO��<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02398<BR>
     * <BR>
     * @@param l_rpcParams - (�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j<BR>
     * @@throws WEB3BaseException
     */
    public void setSOAPConnectionProxy(SoapConnectPrefRpcParams l_rpcParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setSOAPConnectionProxy(SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);

        //����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g����
        //  �������A�ڑ�������擾����B
        // getEndpointName().split(arg0 : String)
        //�@@[����]
        //�@@arg0�F ";"
        String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");

        //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ��
        if (l_strEndpointNames.length == 3)
        {
            //���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ����
            if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
            {
                //�i1�jkey   : "https.proxyHost"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
                System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());

                //�i2�jkey   : "https.proxyPort"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
                System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
            }
            else
            {
                //������L�̏����ȊO����
                // ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
                //�i1�jkey   : "http.proxyHost"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
                System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());

                //�i2�jkey   : "http.proxyPort"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
                System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
            }
        }
        else if (l_strEndpointNames.length == 1)
        {
            //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��
            // �����Ȃ�
        }
        else
        {
            //����L�̏����ȊO��
            //��O���X���[����B
            log.debug("�O���V�X�e���ڑ��G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O���V�X�e���ڑ��G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createGFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d�����ׂ��쐬����B <BR>
     * <BR>
     * �P�jGFT���ʒʒm�d�����ׂ̍��ڂ́A����.GFT�˗��d�����ׂ̓����ڂ̒l���Z�b�g����B <BR>
     * �@@�����ڂ�null�Ȃ��null���Z�b�g�����悤�Ȏd�g�݂�OK�B<BR>
     * <BR>
     * �Q�jGFT��DIR���M�����ɁA���ݓ������Z�b�g����B <BR>
     * �@@Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime()); <BR>
     * <BR>
     * �R�j��t���ʂɁA����.��t���ʃR�[�h���Z�b�g����B <BR>
     * <BR>
     * �S�j�����J�݂̏ꍇ�iFX�������ꗗ != null�j�A <BR>
     * �@@�@@�S�|�P�jGFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ = ����.FX�������ꗗ <BR>
     * <BR>
     * �@@�@@�S�|�Q�jFX�������ꗗ�̗v�f�����ȉ������{����B <BR>
     * �@@�@@�@@�S�|�Q�|�P�j����.FX�������ꗗ[n].�R�[�X�敪==1�̏ꍇ <BR>
     * �@@�@@�@@�@@�ב֕ۏ؋������ԍ��i1���ʉ݁j�� ����.FX�������ꗗ[n].�����ԍ����Z�b�g����B <BR>
     * �@@�@@�@@�S�|�Q�|�Q����.FX�������ꗗ[n].�R�[�X�敪==2�̏ꍇ <BR>
     * �@@�@@�@@�@@�ב֕ۏ؋������ԍ��i10���ʉ݁j�� ����.FX�������ꗗ[n].�����ԍ����Z�b�g����B <BR>
     * <BR>
     * �T�j��������GFT���ʒʒm�d�����ׂ�ԋp����B <BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@param l_fXAccInformationUnits - (FX�������� ��)<BR>
     * FX�������ꗗ<BR>
     * @@param l_strResultCode - (��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     */
    public WEB3FXGftResultNoticeTelegramUnit createGftResultNoticeTelegramUnit(
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit,
        WEB3FXAccInformationUnit[] l_fXAccInformationUnits,
        String l_strResultCode)
    {
        final String STR_METHOD_NAME = "createGftResultNoticeTelegramUnit(WEB3FXGftAskingTelegramUnit, WEB3FXAccInformationUnit[], String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
            new WEB3FXGftResultNoticeTelegramUnit();
        //GFT���ʒʒm�d�����ׂ��쐬����B
        //GFT���ʒʒm�d�����ׂ̍��ڂ́A����.GFT�˗��d�����ׂ̓����ڂ̒l���Z�b�g����B
        String l_strOperationDiv = l_fXGftAskingTelegramUnit.gftOperationDiv;
        //�@@DIR��GFT���M����
        l_fXGftResultNoticeTelegramUnit.dirSendTime = l_fXGftAskingTelegramUnit.dirSendTime;
        //�@@�����敪
        l_fXGftResultNoticeTelegramUnit.gftOperationDiv = l_strOperationDiv;
        //�@@�������O�C��ID
        l_fXGftResultNoticeTelegramUnit.fxFirstLoginId = l_fXGftAskingTelegramUnit.fxFirstLoginId;
        //�@@�S���敪
        l_fXGftResultNoticeTelegramUnit.groupName = l_fXGftAskingTelegramUnit.groupName;
        //�@@��ЃR�[�h
        l_fXGftResultNoticeTelegramUnit.institutionCode = l_fXGftAskingTelegramUnit.institutionCode;
        //�@@WOLF�Z�b�V�����L�[
        l_fXGftResultNoticeTelegramUnit.wolfSession = l_fXGftAskingTelegramUnit.wolfSession;
        //�@@�A�v���P�[�V����ID
        l_fXGftResultNoticeTelegramUnit.wolfAid = l_fXGftAskingTelegramUnit.wolfAid;
        //�@@�Đ����T�[�r�XID
        l_fXGftResultNoticeTelegramUnit.regetServiceId = l_fXGftAskingTelegramUnit.regetServiceId;
        //�@@SSID
        l_fXGftResultNoticeTelegramUnit.wolfSsid = l_fXGftAskingTelegramUnit.wolfSsid;
        //�@@���X�R�[�h
        l_fXGftResultNoticeTelegramUnit.branchCode = l_fXGftAskingTelegramUnit.branchCode;
        //�@@�ڋq�R�[�h
        l_fXGftResultNoticeTelegramUnit.accountCode = l_fXGftAskingTelegramUnit.accountCode;
        //�@@���ʃR�[�h
        l_fXGftResultNoticeTelegramUnit.requestNumber = l_fXGftAskingTelegramUnit.requestNumber;
        //�ב֕ۏ؋������ԍ�
        l_fXGftResultNoticeTelegramUnit.fxAccountCode = l_fXGftAskingTelegramUnit.fxAccountCode;
        //���o���z
        l_fXGftResultNoticeTelegramUnit.cashinoutAmt = l_fXGftAskingTelegramUnit.cashinoutAmt;
        //��n�� 
        l_fXGftResultNoticeTelegramUnit.deliveryDate = l_fXGftAskingTelegramUnit.deliveryDate;
        //�@@���O�i���j
        l_fXGftResultNoticeTelegramUnit.fxLastName = l_fXGftAskingTelegramUnit.fxLastName;
        //���O�i���j
        l_fXGftResultNoticeTelegramUnit.fxFirstName = l_fXGftAskingTelegramUnit.fxFirstName;
        //�@@���[���A�h���X
        l_fXGftResultNoticeTelegramUnit.fxMailAddress = l_fXGftAskingTelegramUnit.fxMailAddress;
        //�@@�����p�X���[�h
        l_fXGftResultNoticeTelegramUnit.fxFirstPassword = l_fXGftAskingTelegramUnit.fxFirstPassword;
        //��ЃR�[�h
        l_fXGftResultNoticeTelegramUnit.institutionCode = l_fXGftAskingTelegramUnit.institutionCode;
        //�n�b�V���l
        l_fXGftResultNoticeTelegramUnit.hashValue = l_fXGftAskingTelegramUnit.hashValue;

        //�Q�jGFT��DIR���M�����ɁA���ݓ������Z�b�g����B 
        //�@@Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime()); 
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime()); 
        l_fXGftResultNoticeTelegramUnit.gftSendTime =
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, 
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        //�R�j��t���ʂɁA����.��t���ʃR�[�h���Z�b�g����B
        l_fXGftResultNoticeTelegramUnit.resultCode = l_strResultCode;

        //�S�j�����J�݂̏ꍇ�iFX�������ꗗ != null ���� FX�������ꗗ.length != 0�j
        if (l_fXAccInformationUnits != null && l_fXAccInformationUnits.length != 0)
        {
            //�@@�@@�S�|�P�jGFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ = ����.FX�������ꗗ
            l_fXGftResultNoticeTelegramUnit.fxAccInformationList = l_fXAccInformationUnits;
            //�@@�@@�S�|�Q�jFX�������ꗗ�̗v�f�����ȉ������{����B
            int l_intLength = l_fXAccInformationUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                WEB3FXAccInformationUnit l_fXAccInformationUnit = l_fXAccInformationUnits[i];
                //�@@�@@�@@�S�|�Q�|�P�j����.FX�������ꗗ[n].�R�[�X�敪==1�̏ꍇ
                if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(l_fXAccInformationUnit.fxCourseDiv))
                {
                    //�@@�@@�@@�@@�ב֕ۏ؋������ԍ��i1���ʉ݁j�� ����.FX�������ꗗ[n].�����ԍ����Z�b�g����B
                    l_fXGftResultNoticeTelegramUnit.gftAcc1 = l_fXAccInformationUnit.fxAccountCode;
                }
                //�@@�@@�@@�S�|�Q�|�Q����.FX�������ꗗ[n].�R�[�X�敪==2�̏ꍇ
                if (WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(l_fXAccInformationUnit.fxCourseDiv))
                {
                    //�@@�@@�@@�@@�ב֕ۏ؋������ԍ��i10���ʉ݁j�� ����.FX�������ꗗ[n].�����ԍ����Z�b�g����B
                    l_fXGftResultNoticeTelegramUnit.gftAcc2 = l_fXAccInformationUnit.fxAccountCode;
                }
            }
        }
        //�T�j��������GFT���ʒʒm�d�����ׂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_fXGftResultNoticeTelegramUnit;
    }
}
@


1.1
log
@*** empty log message ***
@
text
@a165 4
     * �@@�@@�i3�jkey   : "weblogic.webservice.transport.https.proxy.host"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f<BR>
     * �@@�@@�i4�jkey   : "weblogic.webservice.transport.https.proxy.port"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f<BR>
a171 3
     * �@@�@@�i3�jkey   : "weblogic.webservice.transport.http.proxy.host"<BR>
     * �@@�@@�@@�@@value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f<BR>
     * �@@�@@�i4�jkey   : "weblogic.webservice.transport.http.proxy.port"<BR>
a209 10

                //�i3�jkey   : "weblogic.webservice.transport.https.proxy.host"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
                System.setProperty("weblogic.webservice.transport.https.proxy.host",
                    l_strEndpointNames[0].trim());

                //�i4�jkey   : "weblogic.webservice.transport.https.proxy.port"
                //�@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
                System.setProperty("weblogic.webservice.transport.https.proxy.port",
                    l_strEndpointNames[1].trim());
a221 10

                //�i3�jkey   : "weblogic.webservice.transport.http.proxy.host"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
                System.setProperty("weblogic.webservice.transport.http.proxy.host",
                    l_strEndpointNames[0].trim());

                //�i4�jkey   : "weblogic.webservice.transport.http.proxy.port"
                // �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
                System.setProperty("weblogic.webservice.transport.http.proxy.port",
                    l_strEndpointNames[1].trim());
@

