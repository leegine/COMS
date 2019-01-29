head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�XImpl(WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
Revision History    : 2007/06/15 ���G�� (���u) �d�l�ύX ���f��No.099,�c�a�X�V�d�lNo.008
Revision History    : 2007/06/18 �đo�g (���u) �d�l�ύX ���f��No.101,No.109,No.110,No.111,No.112
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminDirMapKeyDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccVoucherRecordDetail;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAccRegVoucherStatUpdService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�XImpl)<BR>
 * �Ǘ��Ҍڋq���o�^�`�[�X�e�[�^�X�X�V�T�[�r�XImpl�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl
    implements WEB3AdminDirSecAccRegVoucherStatUpdService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class);

    /**
     * @@roseuid 466E0B6A026D
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������J�n����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�������()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�������ʉ��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�X�V�m�F���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ<BR>
     * �@@this.get�X�V�������()���R�[������B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 465408AF01E6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminDirSecAccRegVoucherSearchInpRequest)
        {
            //���Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g�̏ꍇ
            //�@@this.get�������()���R�[������B
            l_response =
                this.getSearchScreen(
                    (WEB3AdminDirSecAccRegVoucherSearchInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecAccRegVoucherSearchResRequest)
        {
            //���Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g�̏ꍇ
            //�@@this.get�������ʉ��()���R�[������B
            l_response =
                this.getSearchResultScreen(
                    (WEB3AdminDirSecAccRegVoucherSearchResRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)
        {
            //���Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ
            //�@@this.get�X�V�m�F���()���R�[������B
            l_response =
                this.getUpdateConfirmScreen(
                    (WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)
        {
            //���Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ
            //�@@this.get�X�V�������()���R�[������B
            l_response =
                this.getUpdateCompleteScreen(
                    (WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������)<BR>
     * �ڋq���o�^�`�[���R�[�h������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjget������ʁv�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : 1.4 DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAccRegVoucherSearchInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AE90278
     */
    protected WEB3AdminDirSecAccRegVoucherSearchInpResponse getSearchScreen(
        WEB3AdminDirSecAccRegVoucherSearchInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchScreen(WEB3AdminDirSecAccRegVoucherSearchInpRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �L���[�e�[�u���X�e�[�^�X�X�V�j
        //  �����@@�\�̈ח��p
        //  is�X�V�FTRUE
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //createResponse( )
        WEB3AdminDirSecAccRegVoucherSearchInpResponse l_searchInpResponse =
            (WEB3AdminDirSecAccRegVoucherSearchInpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_searchInpResponse;
    }

    /**
     * (get�������ʉ��)<BR>
     * �ڋq���o�^�`�[���R�[�h�������ʉ�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjget�������ʉ�ʁv�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : 1.5 DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : 1.14 ArrayList�̒��� == 0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02837<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAccRegVoucherSearchResResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AEB02F5
     */
    protected WEB3AdminDirSecAccRegVoucherSearchResResponse getSearchResultScreen(
        WEB3AdminDirSecAccRegVoucherSearchResRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchResultScreen(WEB3AdminDirSecAccRegVoucherSearchResRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �L���[�e�[�u���X�e�[�^�X�X�V�j
        //   �����@@�\�̈ח��p
        // is�X�V�FTRUE
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //ArrayList( )
        ArrayList l_lisReturns = new ArrayList();

        //get�،���ЃR�[�h( )
        String  l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�e��A���e�[�u�����R�[�h(String, String, String, String, String, String, String)
        //[get�e��A���e�[�u�����R�[�h()�Ɏw�肷�����]
        //�،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //�A����ʁFnull
        //���ʃR�[�h�Fnull
        //���X�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.���X�R�[�h
        //�ڋq�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.�ڋq�R�[�h
        //�f�[�^�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.�f�[�^�R�[�h
        //�`�[���M�����F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.�`�[���M��
        List l_lisInformRecords = this.getVariousInformRecord(
            l_strInstitutionCode,
            null,
            null,
            l_request.branchCode,
            l_request.accountCode,
            l_request.dataCode,
            l_request.voucherSendDate);

        //this.get�e��A���e�[�u�����R�[�h ()�̖߂�l���� > 0 �̏ꍇ�A������Loop
        if (l_lisInformRecords != null && !l_lisInformRecords.isEmpty())
        {
            //create�ڋq���o�^�`�[(List, boolean, String, String, ArrayList)
            //[this.create�ڋq���o�^�`�[()�Ɏw�肷�����]
            //  ���R�[�hList�Fthis.get�e��A���e�[�u�����R�[�h()�̖߂�l
            //  �����J�ݓ`�[�t���O�FFALSE
            //�@@���X�R�[�h�Fnull
            //�@@�ڋq�R�[�h�Fnull
            //�@@�ԋp�l�pArrayList�F�u�ڋq���o�^�`�[���R�[�h�v�i�[�pArrayList
            this.createAccInfoRegVoucher(
                l_lisInformRecords,
                false,
                null,
                null,
                l_lisReturns);
        }

        //get���ʃR�[�h(�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g, String)
        //[get���ʃR�[�h�Ɏw�肷�����]
        // l_request�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g
        // �،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        Object[] l_requestNumbers = this.getRequestNumber(l_request, l_strInstitutionCode);

        //get���ʃR�[�h() == null AND �u�ڋq���o�^�`�[���R�[�h�v
        //  �i�[�pArrayList�̒��� ==0 �̏ꍇ�A
        //�u���R�[�h�����݂��܂���B�v�̗�O���X���[����B
        if (l_requestNumbers == null && l_lisReturns.isEmpty())
        {
            log.debug("���R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "���R�[�h�����݂��܂���B");
        }

        //get���ʃR�[�h() != null�̏ꍇ�A�������s���B
        if (l_requestNumbers != null)
        {
            //get�����J�ݓ`�[�X�e�[�^�X���R�[�h(String, Object[], String, String, String)
            //[this.get�����J�ݓ`�[�X�e�[�^�X���R�[�h()�Ɏw�肷�����]
            // �،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h()�̖߂�l
            // ���ʃR�[�h�Fthis.get���ʃR�[�h()�̖߂�l
            // �f�[�^�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.�f�[�^�R�[�h
            // �`�[�ʔԁFnull
            // �`�[���M�����F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.�`�[���M��
            List l_lisStatusRecords = this.getAccOpenVoucherStatusRecord(
                l_strInstitutionCode,
                l_requestNumbers,
                l_request.dataCode,
                null,
                l_request.voucherSendDate);

            //this.get�����J�ݓ`�[�X�e�[�^�X���R�[�h ()�̖߂�l���� > 0 �̏ꍇ�A������Loop
            if (l_lisStatusRecords != null && !l_lisStatusRecords.isEmpty())
            {
                //create�ڋq���o�^�`�[(List, boolean, String, String, ArrayList)
                //[this.create�ڋq���o�^�`�[()�Ɏw�肷�����]
                // ���R�[�hList�Fthis.get�����J�ݓ`�[�X�e�[�^�X���R�[�h()�̖߂�l
                // �����J�ݓ`�[�t���O�FTRUE
                // ���X�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.���X�R�[�h
                // �ڋq�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g.�ڋq�R�[�h
                // �ԋp�l�pArrayList�F�u�ڋq���o�^�`�[���R�[�h�v�i�[�pArrayList
                this.createAccInfoRegVoucher(
                    l_lisStatusRecords,
                    true,
                    l_request.branchCode,
                    l_request.accountCode,
                    l_lisReturns);
            }
        }

        //ArrayList�̒��� == 0�̏ꍇ�A��O���X���[����B
        if (l_lisReturns.isEmpty())
        {
            log.debug("���R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "���R�[�h�����݂��܂���B");
        }

        //createResponse( )
        WEB3AdminDirSecAccRegVoucherSearchResResponse l_searchResResponse =
            (WEB3AdminDirSecAccRegVoucherSearchResResponse)l_request.createResponse();

        WEB3AdminDirSecAccVoucherRecordDetail[] l_recordDetails =
            new WEB3AdminDirSecAccVoucherRecordDetail[l_lisReturns.size()];
        l_lisReturns.toArray(l_recordDetails);

        l_searchResResponse.accVoucherRecord = l_recordDetails;

        log.exiting(STR_METHOD_NAME);
        return l_searchResResponse;
    }

    /**
     * (get�X�V�m�F���)<BR>
     * �ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjget�X�V�m�F��ʁv�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : 1.5 DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAccRegVoucherStatUpdConfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AED0268
     */
    protected WEB3AdminDirSecAccRegVoucherStatUpdConfResponse getUpdateConfirmScreen(
        WEB3AdminDirSecAccRegVoucherStatUpdConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getUpdateConfirmScreen(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �L���[�e�[�u���X�e�[�^�X�X�V�j
        //  �����@@�\�̈ח��p
        //is�X�V�FTRUE
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //createResponse
        WEB3AdminDirSecAccRegVoucherStatUpdConfResponse l_response =
            (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�X�V�������)<BR>
     * �ڋq���o�^�`�[�X�e�[�^�X�X�V������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjget�X�V������ʁv�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : 1.5 DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAccRegVoucherStatUpdCompResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AEF015F
     */
    protected WEB3AdminDirSecAccRegVoucherStatUpdCompResponse getUpdateCompleteScreen(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getUpdateCompleteScreen(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �L���[�e�[�u���X�e�[�^�X�X�V�j
        //  �����@@�\�̈ח��p
        //  is�X�V�FTRUE
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //validate����p�X���[�h(�p�X���[�h : String)
        // [validate����p�X���[�h()�Ɏw�肷�����]
        // �p�X���[�h�@@�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //ArrayList( )
        ArrayList l_lisReturns = new ArrayList();

        //�ڋq���o�^�`�[���R�[�h�̒����̉񐔃��[�v
        //�ڋq���o�^�`�[���R�[�h[index]���e��A���e�[�u�����R�[�h�̏ꍇ
        for (int i = 0; i < l_request.accVoucherRecord.length; i++)
        {
            WEB3AdminDirSecAccVoucherRecordDetail l_recordDetail =
                l_request.accVoucherRecord[i];
            if (!l_recordDetail.voucherFlag)
            {
                //update�e��A���e�[�u��(�ڋq���o�^�`�[���R�[�h�ڍ�, String, String)
                this.updateVariousInform(
                    l_recordDetail,
                    l_request.updateVoucherMakeStatus,
                    l_request.updateErrorReasonCode);

                //get�e��A���e�[�u�����R�[�h(String, String, String, String, String, String, String)
                //[get�e��A���e�[�u�����R�[�h()�Ɏw�肷�����]
                // �،���ЃR�[�h�F�ڋq���o�^�`�[���R�[�h[index].�،���ЃR�[�h
                // �A����ʁF�ڋq���o�^�`�[���R�[�h[index].�A�����
                // ���ʃR�[�h�F�ڋq���o�^�`�[���R�[�h[index].���ʃR�[�h
                // ���X�R�[�h�F�ڋq���o�^�`�[���R�[�h[index].���X�R�[�h
                // �ڋq�R�[�h�Fnull
                // �f�[�^�R�[�h�Fnull
                // �`�[���M�����Fnull
                List l_lisInformRecords = this.getVariousInformRecord(
                    l_recordDetail.institutionCode,
                    l_recordDetail.infoType,
                    l_recordDetail.requestNumber,
                    l_recordDetail.branchCode,
                    null,
                    null,
                    null);

                //create�ڋq���o�^�`�[(List, boolean, String, String, ArrayList)
                //[this.create�ڋq���o�^�`�[()�Ɏw�肷�����]
                // ���R�[�hList�Fthis.get�e��A���e�[�u�����R�[�h()�̖߂�l
                // �����J�ݓ`�[�t���O�FFALSE
                // ���X�R�[�h�Fnull
                // �ڋq�R�[�h�Fnull
                // �ԋp�l�pArrayList�F�u�ڋq���o�^�`�[���R�[�h�v�i�[�pArrayList
                this.createAccInfoRegVoucher(
                    l_lisInformRecords,
                    false,
                    null,
                    null,
                    l_lisReturns);
            }
            else
            {
                //update�����J�ݓ`�[�X�e�[�^�X(�ڋq���o�^�`�[���R�[�h�ڍ�, String, String)
                //[this.update�����J�ݓ`�[�X�e�[�^�X()�Ɏw�肷�����]
                // �ڋq���F�ڋq���o�^�`�[���R�[�h[index]
                // �`�[�쐬�󋵁F�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g.�X�V_�`�[�쐬��
                // �G���[���R�R�[�h�F�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g.�X�V_�G���[���R�R�[�h
                this.updateAccOpenVoucherStatus(
                    l_recordDetail,
                    l_request.updateVoucherMakeStatus,
                    l_request.updateErrorReasonCode);

                //get�����J�ݓ`�[�X�e�[�^�X���R�[�h(String, Object[], String, String, String)
                //[this.get�����J�ݓ`�[�X�e�[�^�X���R�[�h()�Ɏw�肷�����]
                // �،���ЃR�[�h�F�ڋq���o�^�`�[���R�[�h[index].�،���ЃR�[�h
                // ���ʃR�[�h�F�ڋq���o�^�`�[���R�[�h[index].���ʃR�[�h()��v�f�Ƃ�������1��Object�z��
                // �f�[�^�R�[�h�F�ڋq���o�^�`�[���R�[�h[index].�f�[�^�R�[�h
                // �`�[�ʔԁF�ڋq���o�^�`�[���R�[�h[index].�`�[�ʔ�
                // �`�[���M�����Fnull
                List l_lisInformRecords = this.getAccOpenVoucherStatusRecord(
                    l_recordDetail.institutionCode,
                    new Object[]{l_recordDetail.requestNumber},
                    l_recordDetail.dataCode,
                    l_recordDetail.voucherNumber,
                    null);

                //create�ڋq���o�^�`�[(List, boolean, String, String, ArrayList)
                //[this.create�ڋq���o�^�`�[()�Ɏw�肷�����]
                // ���R�[�hList�Fthis.get�����J�ݓ`�[�X�e�[�^�X���R�[�h()�̖߂�l
                // �����J�ݓ`�[�t���O�FTRUE
                // ���X�R�[�h�F�ڋq���o�^�`�[���R�[�h[index].���X�R�[�h
                // �ڋq�R�[�h�F�ڋq���o�^�`�[���R�[�h[index].�ڋq�R�[�h
                // �ԋp�l�pArrayList�F�u�ڋq���o�^�`�[���R�[�h�v�i�[�pArrayList
                this.createAccInfoRegVoucher(
                    l_lisInformRecords,
                    true,
                    l_recordDetail.branchCode,
                    l_recordDetail.accountCode,
                    l_lisReturns);
            }
        }

        //createResponse( )
        WEB3AdminDirSecAccRegVoucherStatUpdCompResponse l_compResponse =
            (WEB3AdminDirSecAccRegVoucherStatUpdCompResponse)l_request.createResponse();

        WEB3AdminDirSecAccVoucherRecordDetail[] l_accVoucherRecords =
            new WEB3AdminDirSecAccVoucherRecordDetail[l_lisReturns.size()];
        l_lisReturns.toArray(l_accVoucherRecords);
        l_compResponse.accVoucherRecord = l_accVoucherRecords;

        log.exiting(STR_METHOD_NAME);
        return l_compResponse;
    }

    /**
     * (get���ʃR�[�h)<BR>
     * �����J�݌����q�e�[�u�����A���ʃR�[�h���擾����B<BR>
     * �擾�o���Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j ����������̍쐬<BR>
     * �@@�P-�P�j ��������������𐶐�<BR>
     * <BR>
     * �@@�@@�@@"institution_code = ? and branch_code= ? and account_code = ?"<BR>
     * <BR>
     * �Q�j ���������R���e�i�̍쐬<BR>
     * �@@�Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     *   �Q-�Q�j �����K�{�����̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����jl_request.���X�R�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����jl_request.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�Q-�R�j ArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * <BR>
     * �R�j �����J�݌����q�e�[�u����背�R�[�h���擾<BR>
     * �@@�R-�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�����J�݌����qRowType<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * <BR>
     * �@@�R-�Q�j �R-�P�j �̖߂�l�i�����J�݌����q�e�[�u��List�j���� > 0 �̏ꍇ�A<BR>
     * �@@�@@�R-�Q-�P�j ArrayList�𐶐�<BR>
     * �@@�@@�R-�Q-�Q�j �����J�݌����q�e�[�u��List�̒����̉�Loop<BR>
     * �@@�@@�@@�R-�Q-�Q-�P�j ���ʃR�[�h�̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�����J�݌����q�e�[�u��List[index].get���ʃR�[�h()<BR>
     * <BR>
     * �@@�@@�R-�Q-�R�j ArrayList#toArray()��ԋp����B<BR>
     * <BR>
     * �@@�R-�R�j �̖߂�l���� == 0 �̏ꍇnull��ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 465A36F302F8
     */
    private Object[] getRequestNumber(
        WEB3AdminDirSecAccRegVoucherSearchResRequest l_request, String l_strInstitutionCode)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getRequestNumber(WEB3AdminDirSecAccRegVoucherSearchResRequest, String)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //�P�j ����������̍쐬
        //�P-�P�j ��������������𐶐�
        //      "institution_code = ? and branch_code= ? and account_code = ?"
        String l_strQueryString = " institution_code = ? and branch_code= ? and account_code = ? ";
        //�Q�j ���������R���e�i�̍쐬
        // �Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisValue = new ArrayList();

        // �Q-�Q�j �����K�{�����̒ǉ�
        //   [add()�Ɏw�肷�����]
        //  �@@  �i�����j�،���ЃR�[�h
        //   [add()�Ɏw�肷�����]
        //      �i�����jl_request.���X�R�[�h
        //   [add()�Ɏw�肷�����]
        //       �����jl_request.�ڋq�R�[�h
        l_lisValue.add(l_strInstitutionCode);
        l_lisValue.add(l_request.branchCode);
        l_lisValue.add(l_request.accountCode);

        // �Q-�R�j ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //�R�j �����J�݌����q�e�[�u����背�R�[�h���擾
        // �R-�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
        //   [doFindAllQuery()�Ɏw�肷�����]
        //     �@@arg0�F�@@�����J�݌����qRowType
        //    �@@ arg1�F�@@�P�j �ō쐬����������
        //  �@@   arg2�F�@@�Q�j �ō쐬�����z��
        List l_lisExpAccountOpenRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisExpAccountOpenRecord = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        // �R-�Q�j �R-�P�j �̖߂�l�i�����J�݌����q�e�[�u��List�j���� > 0 �̏ꍇ�A
        Object[] l_sccOpenRequestNumbers = null;
        if (l_lisExpAccountOpenRecord.size() > 0)
        {
            //  �R-�Q-�P�j ArrayList�𐶐�
            List l_lisTemp = new ArrayList();

            //  �R-�Q-�Q�j �����J�݌����q�e�[�u��List�̒����̉�Loop
            for (int i = 0; i < l_lisExpAccountOpenRecord.size(); i++)
            {
                //  �R-�Q-�Q-�P�j ���ʃR�[�h�̒ǉ�
                //     [add()�Ɏw�肷�����]
                //        �����J�݌����q�e�[�u��List[index].get���ʃR�[�h()
                l_lisTemp.add(((ExpAccountOpenRow)
                    l_lisExpAccountOpenRecord.get(i)).getAccOpenRequestNumber());
            }

            //  �R-�Q-�R�j ArrayList#toArray()��ԋp����B
            l_sccOpenRequestNumbers = new Object[l_lisTemp.size()];
            l_lisTemp.toArray(l_sccOpenRequestNumbers);
        }

        //�R-�R�j �̖߂�l���� == 0 �̏ꍇnull��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sccOpenRequestNumbers;
    }

    /**
     * �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����R�[�h�i<BR>
     * �@@�i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE�j�̎��A<BR>
     * �e��A���e�[�u���̓`�[�쐬�󋵎d�l�́i�����jstatus��<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�̓`�[�쐬�X�e�[�^�X�d�l�ɕϊ�����B<BR>
     * <BR>
     * �P�j �i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE �̏ꍇ<BR>
     * �@@�P-�P�j �i�����jstatus == 3�i��t�����j �̎�<BR>
     * �@@�@@�@@�@@�@@4 �i��M�ρj��ԋp����B<BR>
     * <BR>
     * �@@�P-�Q�j �i�����jstatus == 4�i��t�G���[�j �̎�<BR>
     * �@@�@@�@@�@@�@@6�i��M�G���[�j��ԋp����B<BR>
     * <BR>
     * �@@�P-�R�j �P-�P�j�A�P-�Q�j�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�i�����jstatus��ԋp����B<BR>
     * <BR>
     * �Q�j �i�����j�`�[�쐬�󋵕ϊ��t���O == FALSE �̏ꍇ<BR>
     * �@@�@@�@@�i�����jstatus��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strStatus - (status)<BR>
     * �e��A���e�[�u���̓`�[�쐬��<BR>
     * ����<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�̓`�[�쐬�X�e�[�^�X<BR>
     * @@param l_blnIsVoucherStatusChangeFlag - (�`�[�쐬�󋵕ϊ��t���O)<BR>
     * �`�[�쐬�󋵕ϊ��t���O<BR>
     * <BR>
     * TRUE�F�����J�ݓ`�[�쐬�X�e�[�^�X���R�[�h<BR>
     * <BR>
     * FALSE�F�e��A���e�[�u�����R�[�h�B<BR>
     * <BR>
     * @@return String
     * @@roseuid 465A88B30384
     */
    private String changeStatus(String l_strStatus, boolean l_blnIsVoucherStatusChangeFlag)
    {
        final String STR_METHOD_NAME =
            "changeStatus(String, boolean)";
        log.entering(STR_METHOD_NAME);

        //    �P�j �i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE �̏ꍇ
        if (l_blnIsVoucherStatusChangeFlag)
        {
            //    �P-�P�j �i�����jstatus == 3�i��t�����j �̎�
            //            4 �i��M�ρj��ԋp����B
            if (WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strStatus))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3VoucherStatusDef.RECEIVE_COMPLETE;
            }
            //    �P-�Q�j �i�����jstatus == 4�i��t�G���[�j �̎�
            //            6�i��M�G���[�j��ԋp����B
            else if (WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strStatus))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3VoucherStatusDef.RECEIVE_ERROR;
            }
            //    �P-�R�j �P-�P�j�A�P-�Q�j�ȊO�̏ꍇ�A
            //            �i�����jstatus��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_strStatus;
            }
        }
        else
        {
            //  �Q�j �i�����j�`�[�쐬�󋵕ϊ��t���O == FALSE �̏ꍇ
            //       �i�����jstatus��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_strStatus;
        }
    }

    /**
     * (create�ڋq���o�^�`�[)<BR>
     * ���������ɕԋp�l�pArrayList�Ƀv���p�e�B��ݒ肷��B<BR>
     * <BR>
     * �P�j �i�����j���R�[�hList�̒����̉񐔈ȉ����s���B<BR>
     * �@@�P-�P�j �ڋq���o�^�`�[���R�[�h�ڍ׃I�u�W�F�N�g�𐶐��B<BR>
     * <BR>
     * �@@�P-�Q�j �v���p�e�B�̐ݒ���s���B<BR>
     * �@@�@@�P-�Q-�P�j �i�����j�����J�ݓ`�[�t���O == FALSE �̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�،���ЃR�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].���X�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�ڋq�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�f�[�^�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].���ʃR�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�`�[�쐬��<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�G���[���R�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�`�[���M����<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�`�[��M����<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O =<BR>
     * �@@�@@�@@�i�����j�����J�ݓ`�[�t���O<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�A�����<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = null<BR>
     * <BR>
     * �@@�@@�P-�Q-�Q�j �i�����j�����J�ݓ`�[�t���O == TRUE �̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�،���ЃR�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = �i�����j���X�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = �i�����j�ڋq�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h =<BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�f�[�^�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = <BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].���ʃR�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = <BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�`�[�쐬�X�e�[�^�X<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = <BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�G���[�R�[�h<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = <BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].���M����<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = <BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].��M����<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = <BR>
     * �@@�@@�@@�i�����j�����J�ݓ`�[�t���O<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = null<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = <BR>
     * �@@�@@�@@�i�����j���R�[�hList[index].�`�[�ʔ�<BR>
     * <BR>
     * �@@�P-�R�j �߂�l�pArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�P-�Q�j�Ńv���p�e�B�Z�b�g�ς݂̌ڋq���o�^�`�[���R�[�h�ڍ׃I�u�W�F�N�g<BR>
     * <BR>
     * �Q�j �ԋp�l�pArrayList��ԋp����B<BR>
     * <BR>
     * @@param l_lisRecordList - (���R�[�hList)<BR>
     * �e��A���e�[�u�����R�[�h��List�A����<BR>
     * �����J�ݓ`�[�X�e�[�^�X���R�[�h��List�B<BR>
     * @@param l_blnIsAccOpenVoucherFlag - (�����J�ݓ`�[�t���O)<BR>
     * �����J�ݓ`�[�t���O<BR>
     * <BR>
     * TRUE�FList�̃��R�[�h�������J�ݓ`�[�X�e�[�^�X���R�[�h<BR>
     * FALSE�FList�̃��R�[�h���e��A���e�[�u�����R�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_lisRecords - (�ԋp�l�pArrayList)<BR>
     * �ԋp�l�pArrayList�I�u�W�F�N�g�B<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     * @@roseuid 465AA4FD0085
     */
    private ArrayList createAccInfoRegVoucher(
        List l_lisRecordList,
        boolean l_blnIsAccOpenVoucherFlag,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisRecords) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createAccInfoRegVoucher(List, boolean, String, String, ArrayList)";
        log.entering(STR_METHOD_NAME);

        if (l_lisRecordList == null || l_lisRecords == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //    �P�j �i�����j���R�[�hList�̒����̉񐔈ȉ����s���B
        for (int i = 0; i < l_lisRecordList.size(); i++)
        {
            // �P-�P�j �ڋq���o�^�`�[���R�[�h�ڍ׃I�u�W�F�N�g�𐶐��B
            WEB3AdminDirSecAccVoucherRecordDetail l_dirSecAccVoucherRecordDetail =
                new WEB3AdminDirSecAccVoucherRecordDetail();

            //    �P-�Q�j �v���p�e�B�̐ݒ���s���B
            //      �P-�Q-�P�j �i�����j�����J�ݓ`�[�t���O == FALSE �̏ꍇ�A
            if (!l_blnIsAccOpenVoucherFlag)
            {
                //�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = �i�����j���R�[�hList[index].�،���ЃR�[�h
                VariousInformRow l_variousInformRow = (VariousInformRow)l_lisRecordList.get(i);

                l_dirSecAccVoucherRecordDetail.institutionCode =
                    l_variousInformRow.getInstitutionCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = �i�����j���R�[�hList[index].���X�R�[�h
                l_dirSecAccVoucherRecordDetail.branchCode =
                    l_variousInformRow.getBranchCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = �i�����j���R�[�hList[index].�ڋq�R�[�h
                l_dirSecAccVoucherRecordDetail.accountCode = l_variousInformRow.getAccountCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = �i�����j���R�[�hList[index].�f�[�^�R�[�h
                l_dirSecAccVoucherRecordDetail.dataCode = l_variousInformRow.getRequestCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = �i�����j���R�[�hList[index].���ʃR�[�h
                l_dirSecAccVoucherRecordDetail.requestNumber = l_variousInformRow.getRequestNumber();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = �i�����j���R�[�hList[index].�`�[�쐬��
                l_dirSecAccVoucherRecordDetail.voucherMakeStatus = l_variousInformRow.getStatus();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = �i�����j���R�[�hList[index].�G���[���R�R�[�h
                l_dirSecAccVoucherRecordDetail.errorReasonCode = l_variousInformRow.getErrorReasonCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �i�����j���R�[�hList[index].�`�[���M����
                l_dirSecAccVoucherRecordDetail.voucherSendTimestamp = l_variousInformRow.getSendTimestamp();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �i�����j���R�[�hList[index].�`�[��M����
                l_dirSecAccVoucherRecordDetail.voucherRecvTimestamp = l_variousInformRow.getReceiptTimestamp();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = �i�����j�����J�ݓ`�[�t���O
                l_dirSecAccVoucherRecordDetail.voucherFlag = l_blnIsAccOpenVoucherFlag;

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = �i�����j���R�[�hList[index].�A�����
                l_dirSecAccVoucherRecordDetail.infoType = l_variousInformRow.getInformDiv();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = null
                l_dirSecAccVoucherRecordDetail.voucherNumber = null;
            }
            else
            {
                //      �P-�Q-�Q�j �i�����j�����J�ݓ`�[�t���O == TRUE �̏ꍇ�A
                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = �i�����j���R�[�hList[index].�،���ЃR�[�h
                AccOpenVoucherStatusRow l_accOpenVoucherStatusRow =
                    (AccOpenVoucherStatusRow)l_lisRecordList.get(i);
                l_dirSecAccVoucherRecordDetail.institutionCode =
                    l_accOpenVoucherStatusRow.getInstitutionCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = �i�����j���X�R�[�h
                l_dirSecAccVoucherRecordDetail.branchCode = l_strBranchCode;

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = �i�����j�ڋq�R�[�h
                l_dirSecAccVoucherRecordDetail.accountCode = l_strAccountCode;

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = �i�����j���R�[�hList[index].�f�[�^�R�[�h
                l_dirSecAccVoucherRecordDetail.dataCode = l_accOpenVoucherStatusRow.getRequestCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = �i�����j���R�[�hList[index].���ʃR�[�h
                l_dirSecAccVoucherRecordDetail.requestNumber =
                    l_accOpenVoucherStatusRow.getAccOpenRequestNumber();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = �i�����j���R�[�hList[index].�`�[�쐬�X�e�[�^�X
                l_dirSecAccVoucherRecordDetail.voucherMakeStatus =
                    l_accOpenVoucherStatusRow.getVoucherStatus();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = �i�����j���R�[�hList[index].�G���[�R�[�h
                l_dirSecAccVoucherRecordDetail.errorReasonCode =
                    l_accOpenVoucherStatusRow.getErrorCode();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �i�����j���R�[�hList[index].���M����
                l_dirSecAccVoucherRecordDetail.voucherSendTimestamp =
                    l_accOpenVoucherStatusRow.getSendTimestamp();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �i�����j���R�[�hList[index].��M����
                l_dirSecAccVoucherRecordDetail.voucherRecvTimestamp =
                    l_accOpenVoucherStatusRow.getRecvTimestamp();

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = �i�����j�����J�ݓ`�[�t���O
                l_dirSecAccVoucherRecordDetail.voucherFlag = l_blnIsAccOpenVoucherFlag;

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = null
                l_dirSecAccVoucherRecordDetail.infoType = null;

                //  �@@�@@�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = �i�����j���R�[�hList[index].�`�[�ʔ�
                l_dirSecAccVoucherRecordDetail.voucherNumber =
                    l_accOpenVoucherStatusRow.getSerialNo();
            }

            //    �P-�R�j �߂�l�pArrayList�ɒǉ�����B
            //          [add()�Ɏw�肷�����]
            //              �P-�Q�j�Ńv���p�e�B�Z�b�g�ς݂̌ڋq���o�^�`�[���R�[�h�ڍ׃I�u�W�F�N�g
            l_lisRecords.add(l_dirSecAccVoucherRecordDetail);
        }

        //  �Q�j �ԋp�l�pArrayList��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (update�e��A���e�[�u��)<BR>
     * �X�V�Ώۃ��R�[�h���݃`�F�b�N��A�e��A���e�[�u���̍X�V���s���B<BR>
     * <BR>
     * �P�j ����������̍쐬<BR>
     * <BR>
     * �@@�@@"institution_code = ? and inform_div = ? and request_number = ? and<BR>
     * �@@�@@�@@branch_code = ?"<BR>
     * <BR>
     * �Q�j ���������R���e�i�̍쐬<BR>
     * �@@�Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �@@�Q-�Q�j ���������̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�i�����j�ڋq���.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�i�����j�ڋq���.�A�����<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�i�����j�ڋq���.���ʃR�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�i�����j�ڋq���.���X�R�[�h<BR>
     * <BR>
     * �@@�Q-�R�j ArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * <BR>
     * <BR>
     * �R�j �e��A���e�[�u���֊Y�����R�[�h�̑��݃`�F�b�N���s���B<BR>
     * �@@�R-�P�j �e��A���e�[�u����背�R�[�h���擾<BR>
     * �@@�@@�@@�@@�@@QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�e��A���e�[�u��RowType<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * <BR>
     * �@@�R-�Q�j �R-�P�j �̖߂�l���� == 0<BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�A�u���R�[�h�����݂��܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02837<BR>
     * <BR>
     * �S�j �e��A���e�[�u���̍X�V���s���B<BR>
     * �@@�S-�P�j �X�V���e�i�[�p�C���X�^���X�i�FMap�j�𐶐�<BR>
     * <BR>
     * �@@�S-�Q�j �X�V���e�̒ǉ�<BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@key�F"status"<BR>
     * �@@�@@�@@�@@�@@value�F�i�����j�`�[�쐬��<BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@key�F"error_reason_code"<BR>
     * �@@�@@�@@�@@�@@value�F�i�����j�G���[���R�R�[�h<BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@key�F"receipt_timestamp"<BR>
     * �@@�@@�@@�@@�@@value�F���ݓ���<BR>
     * <BR>
     * �@@�S-�R�j �e��A���e�[�u���̍X�V<BR>
     * �@@�@@�@@�@@�@@QueryProcessor#doUpdateQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@�@@[doUpdateQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�e��A���e�[�u��RowType<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * �@@�@@�@@�@@�@@�@@arg3�F  �S-�Q�j �ō쐬����Map<BR>
     * <BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq���)<BR>
     * �ڋq���o�^�`�[���R�[�h�ڍ׃N���X�B<BR>
     * @@param l_strVoucherCreateStatus - (�`�[�쐬��)<BR>
     * �`�[�쐬�󋵁B<BR>
     * @@param l_strErrorReasonCode - (�G���[���R�R�[�h)<BR>
     * �G���[���R�R�[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 465BD68902FE
     */
    private void updateVariousInform(
        WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo,
        String l_strVoucherCreateStatus,
        String l_strErrorReasonCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " updateVariousInform(WEB3AdminDirSecAccVoucherRecordDetail, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_accountInfo == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }
        //�P�j ����������̍쐬
        //"institution_code = ? and inform_div = ? and request_number = ? and branch_code = ?"
        String l_strQueryString =
            " institution_code = ? and inform_div = ? and request_number = ? and branch_code = ? ";

        //�Q�j ���������R���e�i�̍쐬
        //  �Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisValue = new ArrayList();

        //  �Q-�Q�j ���������̒ǉ�
        //        [add()�Ɏw�肷�����]
        //�@@         �i�����j�ڋq���.�،���ЃR�[�h
        //        [add()�Ɏw�肷�����]
        //           �i�����j�ڋq���.�A�����
        //        [add()�Ɏw�肷�����]
        //�@@         �i�����j�ڋq���.���ʃR�[�h
        //        [add()�Ɏw�肷�����]
        //           �i�����j�ڋq���.���X�R�[�h
        l_lisValue.add(l_accountInfo.institutionCode);
        l_lisValue.add(l_accountInfo.infoType);
        l_lisValue.add(l_accountInfo.requestNumber);
        l_lisValue.add(l_accountInfo.branchCode);

        //  �Q-�R�j ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //�R�j �e��A���e�[�u���֊Y�����R�[�h�̑��݃`�F�b�N���s���B
        //  �R-�P�j �e��A���e�[�u����背�R�[�h���擾
        //          QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
        //          [doFindAllQuery()�Ɏw�肷�����]
        //   �@@        arg0�F�@@�e��A���e�[�u��RowType
        //  �@@�@@       arg1�F�@@�P�j �ō쐬����������
        //�@@  �@@       arg2�F�@@�Q�j �ō쐬�����z��
        List l_lisVariousInformRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisVariousInformRecord =
                l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //  �R-�Q�j �R-�P�j �̖߂�l���� == 0 �̏ꍇ�A�u���R�[�h�����݂��܂���B�v�̗�O���X���[����B
        if (l_lisVariousInformRecord.isEmpty())
        {
            log.debug("���R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "���R�[�h�����݂��܂���B");
        }

        //�S�j �e��A���e�[�u���̍X�V���s���B
        //  �S-�P�j �X�V���e�i�[�p�C���X�^���X�i�FMap�j�𐶐�
        Map l_map = new HashMap();

        //  �S-�Q�j �X�V���e�̒ǉ�
        //        [put()�Ɏw�肷�����]
        //          key�F"status"
        //      value�F�i�����j�`�[�쐬��
        //    [put()�Ɏw�肷�����]
        //      key�F"error_reason_code"
        //      value�F�i�����j�G���[���R�R�[�h
        //    [put()�Ɏw�肷�����]
        //      key�F"receipt_timestamp"
        //          value�F���ݓ���
        l_map.put(WEB3AdminDirMapKeyDef.STATUS, l_strVoucherCreateStatus);
        l_map.put(WEB3AdminDirMapKeyDef.ERROR_REASON_CODE, l_strErrorReasonCode);
        l_map.put(WEB3AdminDirMapKeyDef.RECEIPT_TIMESTAMP, GtlUtils.getSystemTimestamp());

        //  �S-�R�j �e��A���e�[�u���̍X�V
        //          QueryProcessor#doUpdateQuery()���\�b�h���R�[������B
        //          [doUpdateQuery()�Ɏw�肷�����]
        //   �@@        arg0�F�@@�e��A���e�[�u��RowType
        //  �@@�@@       arg1�F�@@�P�j �ō쐬����������
        //�@@  �@@       arg2�F�@@�Q�j �ō쐬�����z��
        //             arg3�F  �S-�Q�j �ō쐬����Map
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                VariousInformRow.TYPE,
                l_strQueryString,
                l_queryDataContainers,
                l_map);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�����J�ݓ`�[�X�e�[�^�X)<BR>
     * �X�V�Ώۃ��R�[�h���݃`�F�b�N��A�����J�ݍ쐬�`�[�X�e�[�^�X�̍X�V���s���B<BR>
     * <BR>
     * �P�j ����������̍쐬<BR>
     * <BR>
     * �@@"institution_code = ? and acc_open_request_number = ?<BR>
     * �@@�@@�@@and request_code = ? and serial_no = ?"<BR>
     * <BR>
     * �Q�j ���������R���e�i�̍쐬<BR>
     * �@@�Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �@@�Q-�Q�j ���������̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�ڋq���.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�ڋq���.���ʃR�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�ڋq���.�f�[�^�R�[�h<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�ڋq���.�`�[�ʔ�<BR>
     * <BR>
     * �@@�Q-�R�j ArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * <BR>
     * �R�j �����J�ݍ쐬�`�[�X�e�[�^�X�֊Y�����R�[�h�̑��݃`�F�b�N���s���B<BR>
     * �@@�R-�P�j �����J�ݓ`�[�X�e�[�^�X��背�R�[�h���擾<BR>
     * �@@�@@�@@�@@�@@QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�����J�ݓ`�[�X�e�[�^�XRowType<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * <BR>
     * �@@�R-�Q�j �R-�P�j �̖߂�l���� == 0 �̏ꍇ�A�u���R�[�h�����݂��܂���B�v<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02837<BR>
     * <BR>
     * �S�j �����J�ݍ쐬�`�[�X�e�[�^�X�̍X�V���s���B<BR>
     * �@@�S-�P�j �`�[�쐬�󋵂̕ϊ����s���B<BR>
     * �@@�@@�@@�@@[this.changeStatus()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@status�F�i�����j�`�[�쐬��<BR>
     * �@@�@@�@@�@@�@@�`�[�쐬�󋵕ϊ��t���O�F�i�����j�ڋq���.�����J�ݓ`�[�t���O<BR>
     * <BR>
     * �@@�S-�Q�j �X�V���e�i�[�p�C���X�^���X�i�FMap�j�𐶐�<BR>
     * <BR>
     * �@@�S-�R�j �X�V���e�̒ǉ�<BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@key�F"voucher_status"<BR>
     * �@@�@@�@@�@@�@@value�F �S-�P�j�ŕϊ��ς݂̓`�[�쐬��<BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@key�F"error_code"<BR>
     * �@@�@@�@@�@@�@@value�F�i�����j�G���[���R�R�[�h<BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@key�F"recv_timestamp"<BR>
     * �@@�@@�@@�@@�@@value�F���ݓ���<BR>
     * <BR>
     * �@@�S-�S�j �����J�ݓ`�[�쐬�X�e�[�^�X�̍X�V<BR>
     * �@@�@@�@@�@@�@@QueryProcessor#doUpdateAllQuery()���\�b�h���R�[������B <BR>
     * �@@�@@�@@�@@�@@[doUpdateQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�����J�ݓ`�[�X�e�[�^�XRowType<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * �@@�@@�@@�@@�@@�@@arg3�F  �S-�R�j �ō쐬����Map<BR>
     * <BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq���)<BR>
     * �ڋq���o�^�`�[���R�[�h�ڍ׃N���X�B<BR>
     * @@param l_strVoucherCreateStatus - (�`�[�쐬��)<BR>
     * �`�[�쐬�󋵁B<BR>
     * @@param l_strErrorReasonCode - (�G���[���R�R�[�h)<BR>
     * �G���[���R�R�[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 465BE35C01CF
     */
    private void updateAccOpenVoucherStatus(
        WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo,
        String l_strVoucherCreateStatus,
        String l_strErrorReasonCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " updateAccOpenVoucherStatus(WEB3AdminDirSecAccVoucherRecordDetail, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_accountInfo == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }
        //�P�j ����������̍쐬
        //"institution_code = ? and acc_open_request_number = ?
        //   and request_code = ? and serial_no = ?"
        String l_strQueryString =
            "institution_code = ? and acc_open_request_number = ?"
            + " and request_code = ? and serial_no = ? ";

        //�Q�j ���������R���e�i�̍쐬
        //  �Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisValue = new ArrayList();

        //  �Q-�Q�j ���������̒ǉ�
        //        [add()�Ɏw�肷�����]
        //�@@         �i�����j�ڋq���.�،���ЃR�[�h
        //        [add()�Ɏw�肷�����]
        //�@@         �i�����j�ڋq���.���ʃR�[�h
        //        [add()�Ɏw�肷�����]
        //           �i�����j�ڋq���.�f�[�^�R�[�h
        //        [add()�Ɏw�肷�����]
        //           �i�����j�ڋq���.�`�[�ʔ�
        l_lisValue.add(l_accountInfo.institutionCode);
        l_lisValue.add(l_accountInfo.requestNumber);
        l_lisValue.add(l_accountInfo.dataCode);
        l_lisValue.add(l_accountInfo.voucherNumber);

        //  �Q-�R�j ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //�R�j �����J�ݍ쐬�`�[�X�e�[�^�X�֊Y�����R�[�h�̑��݃`�F�b�N���s���B
        //  �R-�P�j �����J�ݍ쐬�`�[�X�e�[�^�X��背�R�[�h���擾
        //          QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
        //          [doFindAllQuery()�Ɏw�肷�����]
        //   �@@        arg0�F�@@�����J�ݓ`�[�X�e�[�^�XRowType
        //  �@@�@@       arg1�F�@@�P�j �ō쐬����������
        //�@@  �@@       arg2�F�@@�Q�j �ō쐬�����z��
        //
        List l_lisAccOpenVoucherStatusRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenVoucherStatusRecord =
                l_queryProcessor.doFindAllQuery(
                    AccOpenVoucherStatusRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        // �R-�Q�j �R-�P�j �̖߂�l���� == 0 �̏ꍇ�A�u���R�[�h�����݂��܂���B�v��O���X���[����
        if (l_lisAccOpenVoucherStatusRecord.isEmpty())
        {
            log.debug("���R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "���R�[�h�����݂��܂���B");
        }

        //�S�j �����J�ݍ쐬�`�[�X�e�[�^�X�̍X�V���s��
        //  �S-�P�j �`�[�쐬�󋵂̕ϊ����s���B
        //        [this.changeStatus()�Ɏw�肷�����]
        //          status�F�i�����j�`�[�쐬��
        //          �`�[�쐬�󋵕ϊ��t���O�F�i�����j�ڋq���.�����J�ݓ`�[�t���O
        String l_strChangeStatus =
            this.changeStatus(l_strVoucherCreateStatus, l_accountInfo.voucherFlag);

        //  �S-�Q�j �X�V���e�i�[�p�C���X�^���X�i�FMap�j�𐶐�
        Map l_map = new HashMap();

        //  �S-�R�j �X�V���e�̒ǉ�
        //        [put()�Ɏw�肷�����]
        //          key�F"voucher_status"
        //      value�F �S-�P�j�ŕϊ��ς݂̓`�[�쐬��
        //    [put()�Ɏw�肷�����]
        //      key�F"error_code"
        //      value�F�i�����j�G���[���R�R�[�h
        //    [put()�Ɏw�肷�����]
        //      key�F"recv_timestamp"
        //          value�F���ݓ���
        l_map.put(WEB3AdminDirMapKeyDef.VOUCHER_STATUS, l_strChangeStatus);
        l_map.put(WEB3AdminDirMapKeyDef.ERROR_CODE, l_strErrorReasonCode);
        l_map.put(WEB3AdminDirMapKeyDef.RECV_TIMESTAMP, GtlUtils.getSystemTimestamp());

        //  �S-�S�j �����J�ݍ쐬�`�[�e�[�u���̍X�V
        //          QueryProcessor#doUpdateAllQuery()���\�b�h���R�[������B
        //          [doUpdateQuery()�Ɏw�肷�����]
        //   �@@        arg0�F�@@�����J�ݓ`�[�X�e�[�^�XRowType
        //  �@@�@@       arg1�F�@@�P�j �ō쐬����������
        //�@@  �@@       arg2�F�@@�Q�j �ō쐬�����z��
        //             arg3�F  �S-�R�j �ō쐬����Map

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                AccOpenVoucherStatusRow.TYPE,
                l_strQueryString,
                l_queryDataContainers,
                l_map);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�e��A���e�[�u�����R�[�h)<BR>
     * �e��A���e�[�u����背�R�[�h���擾����B<BR>
     * �擾�o���Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j ����������̍쐬<BR>
     * �@@�P-�P�j ��������������ҏW�p�C���X�^���X�i�FStringBuffer�j�𐶐�<BR>
     * <BR>
     * �@@�P-�Q�j �،���ЃR�[�h�i�����K�{�����j�̒ǉ�<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"institution_code = ?"<BR>
     * <BR>
     * �@@�P-�R�j �A����ʏ����̒ǉ�<BR>
     * �@@�@@�P-�R-�P�j �i�����j�A����� != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" and inform_div = ?"<BR>
     * <BR>
     * �@@�P-�S�j ���ʃR�[�h�����ǉ�<BR>
     * �@@�@@�P-�S-�P�j �i�����j���ʃR�[�h != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" and request_number = ?"<BR>
     * <BR>
     * �@@�P-�T�j ���X�R�[�h�i�����K�{�����j�̒ǉ�<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" and branch_code = ?"<BR>
     * <BR>
     * �@@�P-�U�j �ڋq�R�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�P-�U-�P�j �i�����j�ڋq�R�[�h != null AND �i�����j�ڋq�R�[�h�̒��� < 7 �̏ꍇ<BR>
     *   <BR>
     * �@@�@@�@@�@@�@@" and account_code like ?"<BR>
     * <BR>
     * �@@�@@�P-�U-�Q�j �i�����j�ڋq�R�[�h != null AND �i�����j�ڋq�R�[�h�̒��� == 7 �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" and account_code = ?"<BR>
     * <BR>
     * �@@�P-�V�j �f�[�^�R�[�h�����ǉ�<BR>
     * �@@�@@�P-�V-�P�j �i�����j�f�[�^�R�[�h != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" and request_code = ?"<BR>
     * <BR>
     * �@@�@@�P-�V-�Q�j �i�����j�f�[�^�R�[�h == null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" request_code is not null"<BR>
     * <BR>
     * �@@�P-�W�j �`�[���M���������ǉ�<BR>
     * �@@�@@�P-�W-�P�j �i�����j�`�[���M���� != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@" and to_char(send_timestamp, 'YYYYMMDD') = ?"<BR>
     * <BR>
     * �@@�P-�X�j ��������������C���X�^���X��String�ɕϊ�<BR>
     * <BR>
     * <BR>
     * �Q�j ���������R���e�i�̍쐬<BR>
     * �@@�Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �@@�Q-�Q�j �،���ЃR�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�،���ЃR�[�h<BR>
     * <BR>
     * �@@�Q-�R�j �A����ʏ����ǉ�<BR>
     * �@@�@@�Q-�R-�P�j �i�����j�A����� != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�A�����<BR>
     * <BR>
     * �@@�Q-�S�j ���ʃR�[�h�����ǉ�<BR>
     * �@@�@@�Q-�S-�P�j �i�����j���ʃR�[�h != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j���ʃR�[�h<BR>
     * <BR>
     * �@@�Q-�T�j ���X�R�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j���X�R�[�h<BR>
     * <BR>
     * �@@�Q-�U�j �ڋq�R�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�Q-�U-�P�j �i�����j�ڋq�R�[�h != null AND �i�����j�ڋq�R�[�h�̒��� < 7 �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�ڋq�R�[�h + "%"<BR>
     * <BR>
     * �@@�@@�Q-�U-�Q�j �i�����j�ڋq�R�[�h != null AND �i�����j�ڋq�R�[�h�̒��� == 7 �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�ڋq�R�[�h<BR>
     * <BR>
     * �@@�Q-�V�j �f�[�^�R�[�h�����ǉ�<BR>
     * �@@�@@�Q-�V-�P�j �i�����j�f�[�^�R�[�h != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�f�[�^�R�[�h<BR>
     * <BR>
     * �@@�Q-�W�j �`�[���M���������ǉ�<BR>
     * �@@�@@�Q-�W-�P�j �i�����j�`�[���M���� != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�`�[���M����<BR>
     * <BR>
     * �@@�Q-�X�j ArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * <BR>
     * <BR>
     * �R�j �e��A���e�[�u����背�R�[�h���擾<BR>
     * �@@�R-�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@arg0�F�@@�e��A���e�[�u��RowType<BR>
     * �@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * <BR>
     * �S�j �R�j �̖߂�l��ԋp����B�߂�l�̒���==0�̏ꍇ��null��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A����ʁB<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h�B<BR>
     * @@param l_strSendTimestamp - (�`�[���M����)<BR>
     * �`�[���M�����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 465CDED8002F
     */
    private List getVariousInformRecord(
        String l_strInstitutionCode,
        String l_strInformDiv,
        String l_strRequestNumber,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strDataCode,
        String l_strSendTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getVariousInformRecord(String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j ����������̍쐬
        //�P-�P�j ��������������ҏW�p�C���X�^���X�i�FStringBuffer�j�𐶐�
        StringBuffer l_sbQueryString = new StringBuffer();

        //���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisValue = new ArrayList();

        //�P-�Q�j �،���ЃR�[�h�i�����K�{�����j�̒ǉ�
        //      "institution_code = ?"
        //     �i�����j�،���ЃR�[�h
        l_sbQueryString.append(" institution_code = ? ");
        l_lisValue.add(l_strInstitutionCode);

        //�P-�R�j �A����ʏ����̒ǉ�
        //  �P-�R-�P�j �i�����j�A����� != null �̏ꍇ
        //      " and inform_div = ?"
        //     �i�����j�A�����
        if (l_strInformDiv != null)
        {
            l_sbQueryString.append(" and inform_div = ? ");
            l_lisValue.add(l_strInformDiv);
        }

        //�P-�S�j ���ʃR�[�h�����ǉ�
        //  �P-�S-�P�j �i�����j���ʃR�[�h != null �̏ꍇ
        //      " and request_number = ?"
        //      �i�����j���ʃR�[�h
        if (l_strRequestNumber != null)
        {
            l_sbQueryString.append(" and request_number = ? ");
            l_lisValue.add(l_strRequestNumber);
        }

        //�P-�T�j ���X�R�[�h�i�����K�{�����j�̒ǉ�
        //      " and branch_code = ?"
        //       �i�����j���X�R�[�h
        l_sbQueryString.append(" and branch_code = ? ");
        l_lisValue.add(l_strBranchCode);

        //�P-�U�j �ڋq�R�[�h�i�����K�{�����j�̒ǉ�
        //  �P-�U-�P�j �i�����j�ڋq�R�[�h != null AND �i�����j�ڋq�R�[�h�̒��� < 7 �̏ꍇ
        //      " and account_code like ?"
        //      �i�����j�ڋq�R�[�h + "%"
        if (l_strAccountCode != null && l_strAccountCode.length() < 7)
        {
            l_sbQueryString.append(" and account_code like ? ");
            l_lisValue.add(l_strAccountCode + "%");
        }

        //  �P-�U-�Q�j �i�����j�ڋq�R�[�h != null AND �i�����j�ڋq�R�[�h�̒��� == 7 �̏ꍇ
        //      " and account_code = ?"
        //      �i�����j�ڋq�R�[�h
        if (l_strAccountCode != null && l_strAccountCode.length() == 7)
        {
            l_sbQueryString.append(" and account_code = ? ");
            l_lisValue.add(l_strAccountCode);
        }

        //�P-�V�j �f�[�^�R�[�h�����ǉ�
        //  �P-�V-�P�j �i�����j�f�[�^�R�[�h != null �̏ꍇ
        //      " and request_code = ?"
        //      �i�����j�f�[�^�R�[�h
        //  �P-�V-�Q�j �i�����j�f�[�^�R�[�h == null �̏ꍇ
        //      " request_code is not null"
        if (l_strDataCode != null)
        {
            l_sbQueryString.append(" and request_code = ? ");
            l_lisValue.add(l_strDataCode);
        }
        else
        {
            l_sbQueryString.append(" and request_code is not null ");
        }

        //�P-�W�j �`�[���M���������ǉ�
        //  �P-�W-�P�j �i�����j�`�[���M���� != null �̏ꍇ
        //      " and to_char(send_timestamp, 'YYYYMMDD') = ?"
        //      �i�����j�`�[���M����
        if (l_strSendTimestamp != null)
        {
            l_sbQueryString.append(" and to_char(send_timestamp, 'YYYYMMDD') = ? ");
            l_lisValue.add(l_strSendTimestamp);
        }

        //    �P-�X�j ��������������C���X�^���X��String�ɕϊ�
        String l_strQueryString = l_sbQueryString.toString();

        //    �Q-�X�j ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //  �R�j �e��A���e�[�u����背�R�[�h���擾
        //    �R-�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
        //          [doFindAllQuery()�Ɏw�肷�����]
        //     �@@      arg0�F�@@�e��A���e�[�u��RowType
        //    �@@�@@     arg1�F�@@�P�j �ō쐬����������
        //  �@@  �@@     arg2�F�@@�Q�j �ō쐬�����z��
        //  �S�j �R�j �̖߂�l��ԋp����B�߂�l�̒���==0�̏ꍇ��null��ԋp����B
        List l_lisVariousInformRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisVariousInformRecord =
                l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //�R-�P�j �̖߂�l��ԋp����B�߂�l�̒���==0�̏ꍇ��null��ԋp����
        if (l_lisVariousInformRecord.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisVariousInformRecord;
    }

    /**
     * (get�����J�ݓ`�[�X�e�[�^�X���R�[�h)<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X��背�R�[�h���擾����B<BR>
     * �擾�o���Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j ����������̍쐬<BR>
     * �@@�P-�P�j ��������������ҏW�p�C���X�^���X�i�FStringBuffer�j�𐶐�<BR>
     * <BR>
     * �@@�P-�Q�j �،���ЃR�[�h�i�����K�{�����j�̒ǉ�<BR>
     * <BR>
     * �@@�@@�@@�@@"institution_code = ?"<BR>
     * <BR>
     * �@@�P-�R�j ���ʃR�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�P-�R-�P�j ���ʃR�[�h[]�̒��� == 1 �̏ꍇ<BR>
     * �@@�@@�@@�@@" and acc_open_request_number = ?"<BR>
     * <BR>
     * �@@�@@�P-�R-�Q�j ���ʃR�[�h[]�̒��� > 1 �̏ꍇ<BR>
     * �@@�@@�@@�@@" and acc_open_request_number in (?, ?, ?,�c)"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@ ��"?, "�͎��ʃR�[�h[]�̒����̉񐔌J��Ԃ�<BR>
     * <BR>
     * �@@�P-�S�j �f�[�^�R�[�h�����ǉ�<BR>
     * �@@�@@�P-�S-�P�j �i�����j�f�[�^�R�[�h != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@" and request_code = ?"<BR>
     * <BR>
     * �@@�P-�T�j �`�[�ʔԏ����ǉ�<BR>
     * �@@�@@�P-�T-�P�j �i�����j�`�[�ʔ� != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@" and serial_no = ?"<BR>
     * <BR>
     * �@@�P-�U�j �`�[���M���������ǉ�<BR>
     * �@@�@@�P-�U-�P�j �i�����j�`�[���M���� != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@" and to_char(send_timestamp, 'YYYYMMDD') = ?"<BR>
     * <BR>
     * �@@�P-�V�j ��������������C���X�^���X��String�ɕϊ�<BR>
     * <BR>
     * <BR>
     * �Q�j ���������R���e�i�̍쐬<BR>
     * �@@�Q-�P�j ���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �@@�Q-�Q�j �،���ЃR�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�،���ЃR�[�h<BR>
     * <BR>
     * �@@�Q-�R�j ���ʃR�[�h�i�����K�{�����j�̒ǉ�<BR>
     * �@@�@@�Q-�R-�P�j ���ʃR�[�h[]�̒��� == 1 �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j���ʃR�[�h[0]<BR>
     * <BR>
     * �@@�@@�Q-�R-�Q�j ���ʃR�[�h[]�̒��� > 1 �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j���ʃR�[�h[index]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������̉񐔒ǉ�����<BR>
     * <BR>
     * �@@�Q-�S�j �f�[�^�R�[�h�����ǉ�<BR>
     * �@@�@@�Q-�S-�P�j �i�����j�f�[�^�R�[�h != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�f�[�^�R�[�h<BR>
     * <BR>
     * �@@�Q-�T�j �`�[�ʔԏ����ǉ�<BR>
     * �@@�@@�Q-�T-�P�j �i�����j�`�[�ʔ� != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�`�[�ʔ�<BR>
     * <BR>
     * �@@�Q-�U�j �`�[���M���������ǉ�<BR>
     * �@@�@@�Q-�U-�P�j �i�����j�`�[���M���� != null �̏ꍇ<BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�i�����j�`�[���M����<BR>
     * <BR>
     * �@@�Q-�V�j ArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * <BR>
     * <BR>
     * �R�j �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u����背�R�[�h���擾<BR>
     * �@@�R-�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@�����J�ݓ`�[�쐬�X�e�[�^�XRowType<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�P�j �ō쐬����������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�Q�j �ō쐬�����z��<BR>
     * <BR>
     * �@@�R-�Q�j �R-�P�j�̖߂�l��ԋp����B�߂�l�̒���==0�̏ꍇ��null��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_requestNumbers - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h�B<BR>
     * @@param l_strSerialNo - (�`�[�ʔ�)<BR>
     * �`�[�ʔԁB<BR>
     * @@param l_strSendTimestamp - (�`�[���M����)<BR>
     * �`�[���M�����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 465CF74C0230
     */
    private List getAccOpenVoucherStatusRecord(
        String l_strInstitutionCode,
        Object[] l_requestNumbers,
        String l_strDataCode,
        String l_strSerialNo,
        String l_strSendTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getAccOpenVoucherStatusRecord(String, Object[], String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_requestNumbers == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //�P�j ����������̍쐬
        //�P-�P�j ��������������ҏW�p�C���X�^���X�i�FStringBuffer�j�𐶐�
        StringBuffer l_sbQueryString = new StringBuffer();

        //���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisValue = new ArrayList();

        //�P-�Q�j �،���ЃR�[�h�i�����K�{�����j�̒ǉ�
        //      "institution_code = ?"
        //      �i�����j�،���ЃR�[�h
        l_sbQueryString.append(" institution_code = ? ");
        l_lisValue.add(l_strInstitutionCode);

        //�P-�R�j ���ʃR�[�h�i�����K�{�����j�̒ǉ�
        //  �P-�R-�P�j ���ʃR�[�h[]�̒��� == 1 �̏ꍇ
        //      " and acc_open_request_number = ?"
        //      �i�����j���ʃR�[�h[0]
        if (l_requestNumbers.length == 1)
        {
            l_sbQueryString.append(" and acc_open_request_number = ? ");
            l_lisValue.add(l_requestNumbers[0]);
        }

        //  �P-�R-�Q�j ���ʃR�[�h[]�̒��� > 1 �̏ꍇ
        //      " and acc_open_request_number in (?, ?, ?,�c)"
        //                 ��"?, "�͎��ʃR�[�h[]�̒����̉񐔌J��Ԃ�
        //      �������̉񐔒ǉ�����
        else if (l_requestNumbers.length > 1)
        {
            l_sbQueryString.append(" and acc_open_request_number in (");
            for (int i = l_requestNumbers.length - 1; i >= 0; i--)
            {
                l_sbQueryString.append("?");
                l_lisValue.add(l_requestNumbers[i]);
                if (i > 0)
                {
                    l_sbQueryString.append(", ");
                }
            }
            l_sbQueryString.append(") ");
        }

        //�P-�S�j �f�[�^�R�[�h�����ǉ�
        //  �P-�S-�P�j �i�����j�f�[�^�R�[�h != null �̏ꍇ
        //      " and request_code = ?"
        //      �i�����j�f�[�^�R�[�h
        if (l_strDataCode != null)
        {
            l_sbQueryString.append(" and request_code = ? ");
            l_lisValue.add(l_strDataCode);
        }

        //�P-�T�j �`�[�ʔԏ����ǉ�
        //  �P-�T-�P�j �i�����j�`�[�ʔ� != null �̏ꍇ
        //      " and serial_no = ?"
        //      �i�����j�`�[�ʔ�
        if (l_strSerialNo != null)
        {
            l_sbQueryString.append(" and serial_no = ? ");
            l_lisValue.add(l_strSerialNo);
        }

        //�P-�U�j �`�[���M���������ǉ�
        //  �P-�U-�P�j �i�����j�`�[���M���� != null �̏ꍇ
        //      " and to_char(send_timestamp, 'YYYYMMDD') = ?"
        //      �i�����j�`�[���M����
        if (l_strSendTimestamp != null)
        {
            l_sbQueryString.append(" and to_char(send_timestamp, 'YYYYMMDD') = ? ");
            l_lisValue.add(l_strSendTimestamp);
        }

        //    �P-�V�j ��������������C���X�^���X��String�ɕϊ�
        String l_strQueryString = l_sbQueryString.toString();

        //    �Q-�V�j ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];

        l_lisValue.toArray(l_queryDataContainers);

        //  �R�j �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u����背�R�[�h���擾
        //    �R-�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
        //          [doFindAllQuery()�Ɏw�肷�����]
        //     �@@      arg0�F�@@�����J�ݓ`�[�쐬�X�e�[�^�XRowType
        //    �@@�@@     arg1�F�@@�P�j �ō쐬����������
        //  �@@  �@@     arg2�F�@@�Q�j �ō쐬�����z��
        //    �R-�Q�j �R-�P�j �̖߂�l��ԋp����B�߂�l�̒���==0�̏ꍇ��null��ԋp����
        List l_lisAccOpenVoucherStatusRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenVoucherStatusRecord =
                l_queryProcessor.doFindAllQuery(
                    AccOpenVoucherStatusRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //�R-�P�j �̖߂�l��ԋp����B�߂�l�̒���==0�̏ꍇ��null��ԋp����
        if (l_lisAccOpenVoucherStatusRecord.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisAccOpenVoucherStatusRecord;
    }
}
@
