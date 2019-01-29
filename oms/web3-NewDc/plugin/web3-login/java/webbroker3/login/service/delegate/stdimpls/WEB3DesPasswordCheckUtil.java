head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DesPasswordCheckUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright : (��)��a���� �،��\�����[�V�����V�X�e����� 
 * File Name : �V�p�X���[�h�`�F�b�NUTIL(WEB3DesPasswordCheckUtil.java) 
 * Author Name : Daiwa Institute of Research 
 * Revesion History : 2007/07/25 ���ƑS(FLJ) �V�K�쐬
 */

package webbroker3.login.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginPlugin;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.*;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �������\�ȕ��@@�ňÍ������ꂽ�p�X���[�h�ŔF��
 * 
 * @@author  ��
 * @@version 1.0
 */
public class WEB3DesPasswordCheckUtil
{

    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3DesPasswordCheckUtil.class);

    public static final String DES_PWD_CHECK_KEY = "despwdcheck.";

    private static WEB3DesPasswordCheckUtil _instance = new WEB3DesPasswordCheckUtil();

    private WEB3DesPasswordCheckUtil()
    {
    }

    public static WEB3DesPasswordCheckUtil getInstance()
    {
        return _instance;
    }

    /**
     * �������\�ȕ��@@�ňÍ������ꂽ�p�X���[�h�ŔF�� 
     * �@@�V�p�X���[�h�`�F�b�N���{��Б������`�F�b�N�A���{���Ȃ��ꍇ�A���\�b�h����I���B
     * �A�Í������ꂽ�p�X���[�h�iLOGIN_ATTRIBUTE.WEB3_ENCRYPTED_PASSWORD�j���擾�A�擾�ł��Ȃ��ꍇ(Null)�A�G���[�Ƃ���B
     * �B���̓p�X���[�h���Í������āA�A�Ŏ擾�����p�X���[�h�Ɣ�r����B ��r��v�łȂ��ꍇ�A�G���[�Ƃ���B
     * 
     * �ȏ�̑���ŃG���[������ꍇ�A���O�C���G���[�����X�V���āi�G���[�񐔍X�V�j�A���O�C���F�؈ُ���X���[����
     * �G���[�Ȃ��̏ꍇ�A���\�b�h����I���B
     * 
     * @@param iCode
     *            �،���ЃR�[�h
     * @@param l_pwd
     *            ���̓p�X���[�h
     * @@param l_loginInfo
     *            ���O�C�����
     * @@throws WEB3BusinessLayerException
     *             �Í����p�X���[�h�擾�ł��Ȃ��ꍇ�A�����̓p�X���[�h�s��v�̏ꍇ�A�X���[����
     */
    public void checkDesPassword(String l_iCode,String l_pwd, LoginInfo l_loginInfo)
            throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "checkDesPassword(String,String,Map)";
        log.entering(STR_METHOD_NAME);
        
        //�@@�V�p�X���[�h�`�F�b�N���{��Б������`�F�b�N�A���{���Ȃ��ꍇ�A���\�b�h����I���B
        String l_checkFlg = GtlUtils.getTradingSystem().getPreference(DES_PWD_CHECK_KEY + l_iCode);
        log.debug(l_iCode+" checkFlg:"+l_checkFlg);
        
        if ("true".equals(l_checkFlg))
        {
            //�A�Í������ꂽ�p�X���[�h�iLOGIN_ATTRIBUTE.WEB3_ENCRYPTED_PASSWORD�j���擾�A�擾�ł��Ȃ��ꍇ(Null)�A�G���[�Ƃ���B
            Object l_objEncryptedPassword = l_loginInfo.getAttributes().get(WEB3LoginAttributeKeyDef.PASSWORD);
            log.debug("pwd of attributes:"+l_objEncryptedPassword);

            //�B���̓p�X���[�h���Í������āA�A�Ŏ擾�����p�X���[�h�Ɣ�r����B ��r��v�łȂ��ꍇ�A�G���[�Ƃ���B
            String l_encryptPwd = WEB3AcceptPasswordCheckUtil.getInstance().encrypt(l_pwd);
            log.debug("pwd encrypted:"+l_encryptPwd);
            
            //�G���[������ꍇ
            if (l_objEncryptedPassword == null || !l_objEncryptedPassword.equals(l_encryptPwd))
            {
                //���O�C���G���[�����X�V���āi�G���[�񐔍X�V�j
                try
                {
                    updateBadPasswordInfo(l_loginInfo);
                }
                catch (DataException e)
                {
                    log.error("Error while updating bad password info.", e);
                }
                
                //���O�C���F�؈ُ���X���[����
                log.debug(STR_METHOD_NAME + " .... login failed(authenticate).");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_90203, getClass().getName() + "."
                        + STR_METHOD_NAME, "�ڋq(xNAME: " + l_loginInfo.getUsername() + ") �F�؃G���[.");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���[�U�̃��O�C�����̃��O�C���G���[�񐔂��X�V����B
     * 
     * �@@���O�C����񂩂烍�O�C�����R�[�h���擾 
     * �A�����G���[�񐔂ɂP���v���X���āA�G���[�񐔍��ڂɐݒ�B�i�����G���[�񐔂�null�̏ꍇ�A�P��ݒ�j
     * �BDB���X�V����
     * 
     * @@param l_loginInfo
     *            ���O�C�����
     * @@return int �X�V�s��
     * @@throws DataException
     *             DB�G���[����������ꍇ
     */
    private void updateBadPasswordInfo(LoginInfo l_loginInfo) throws DataException
    {
        final String STR_METHOD_NAME = "updateBadPasswordInfo(String)";
        log.entering(STR_METHOD_NAME);

        final QueryProcessor qp = Processors.getDefaultProcessor();

        //�@@���[�U�����烍�O�C�������擾
//        LoginUsernameRow l_usr = LoginUsernameDao.findRowByPk(l_uname);
//        LoginRow loginRow = LoginDao.findRowByPk(l_usr.getLoginId());
        LoginRow loginRow = LoginDao.findRowByPk(l_loginInfo.getLoginId());
        
        final LoginParams loginParams = new LoginParams(loginRow);

        //�A�����G���[�񐔂ɂP���v���X���āA�G���[�񐔍��ڂɐݒ�B�i�����G���[�񐔂�null�̏ꍇ�A�P��ݒ�j
        Date l_now = new Date();
        Integer failureCount = null;
        if (loginRow.getFailureCountIsNull())
        {
            failureCount = new Integer(1);
        }
        else
        {
            if(loginRow.getLastFailureTimestamp()==null || l_now.getTime() > loginRow.getLastFailureTimestamp().getTime() + getLockoutIntervalMillis(loginRow.getTypeId()))
            {
                failureCount = new Integer(1);               
            }
            else
            {
                failureCount = new Integer(loginRow.getFailureCount() + 1);
            }
        }
        loginParams.setFailureCount(failureCount);
        loginParams.setLastFailureTimestamp(l_now);

        //�BDB���X�V����
        qp.doTransaction(1, new TransactionCallback()
        {
            public Object process() throws DataQueryException, DataNetworkException
            {
                qp.lockAccount(loginParams.getLoginId(), true);
                int updated = qp.doUpdateQuery(loginParams);
                return new Integer(updated);
            }

        });

        log.exiting(STR_METHOD_NAME);
    }
    
    private long getLockoutIntervalMillis(long loginTypeId)
        throws DataException
    {
        LoginTypeRow loginTypeRow = LoginTypeDao.findRowByPk(loginTypeId);
        
        return OpLoginPlugin.getLockoutIntervalMillis(loginTypeRow.getTypeName());
    }


}@
