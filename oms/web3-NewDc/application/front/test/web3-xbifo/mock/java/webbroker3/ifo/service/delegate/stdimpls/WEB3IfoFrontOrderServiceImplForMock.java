head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFrontOrderServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����T�[�r�XImplForMock(WEB3IfoFrontOrderServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/29 ���G�� (���u) �V�K�쐬
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �敨OP�����T�[�r�XImplForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoFrontOrderServiceImplForMock extends WEB3IfoFrontOrderServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoFrontOrderServiceImplForMock.class);

    /**
     * (is�s��ʒm������IN�x�e���ԑ�_ForMock)<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone",
            new Class[] {IfoOrderUnit.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            log.exiting(STR_METHOD_NAME);
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);

    }


    /**
     * (get�敨OP��������L���[_ForMock)<BR>
     * �w��̒����P�ʂɊY������敨OP��������L���[���擾���Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return HostFotypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll(
        IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHostFotypeOrderAll(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}))
            {

            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3�jMockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (HostFotypeOrderAllParams)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}).asObject();
            }

        log.exiting(STR_METHOD_NAME);
        return super.getHostFotypeOrderAll(l_orderUnit);
    }

    /**
     * (get����������Rev_ForMock)<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A<BR>
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B<BR>
     * @@param l_orderUnit - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j<BR>
     * ���X�V�C���^�Z�v�^.mutate()��������R�[������邱�Ƃ�O��Ƃ��Ă���B<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderRev(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeOrderRev",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeOrderRev",
            new Class[] {IfoOrderUnit.class}))
        {
        
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeOrderRev",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3�jMockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeOrderRev",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getChangeOrderRev(l_orderUnit);
    }

    /**
     * (get��������������o�H�敪(Mock))<BR>
     * �����̒�������Ώۂ̒����P�ʃI�u�W�F�N�g���A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * �|�����Ƃ��āA���ݗL���Ȕ����o�H��ԋp����B(BR)
     * �|�����o�H�ύX�s�̌o�H��ʂ��Ĕ������������̏ꍇ�́A�������̌o�H��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeSubmitOrderRouteDiv(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeSubmitOrderRouteDiv",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {IfoOrderUnit.class}))
            {

            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3�jMockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {IfoOrderUnit.class}).asObject();
            }

        log.exiting(STR_METHOD_NAME);
        return super.getChangeSubmitOrderRouteDiv(l_orderUnit);
    }
    
    public String getOrderMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
				new Class[] { IfoOrderUnit.class }, new Object[] { l_orderUnit });
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
				new Class[] { IfoOrderUnit.class }))
		{
			log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImplForMock.getOrderMQDataCode(IfoOrderUnit)");

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
					new Class[] { IfoOrderUnit.class }).asWEB3BaseException();

			return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
					new Class[] { IfoOrderUnit.class }).asObject();

		}
		return super.getOrderMQDataCode(l_orderUnit);
	}


    /**
     * (get�����o�H�敪(Mock))<BR>
     * �����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * WEB�V�̎s��R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        String l_strInstitutionCode,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubmitOrderRouteDiv(String, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv",
            new Class[] {String.class, String.class},
            new Object[]{l_strInstitutionCode, l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class}))
            {
            
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class}).asWEB3BaseException();

            //3�jMockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class}).asObject();
            }

        log.exiting(STR_METHOD_NAME);
        return super.getSubmitOrderRouteDiv(l_strInstitutionCode, l_strMarketCode);
    }

    /**
     * (get�t�����g����������敪�R�[�h(Mock))<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode",
            new Class[] {String.class},
            new Object[]{l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode",
            new Class[] {String.class}))
        {
            //2)MockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getFrontOrderExchangeCode",
                new Class[] {String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getFrontOrderExchangeCode(l_strMarketCode);
    }

    /**
     * (get�t�����g�����V�X�e���敪(Mock))<BR>
     * �����̎s��R�[�h�ɂ��A�t�����g�����V�X�e���敪���擾���ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * WEB�V�̎s��R�[�h<BR>
     * @@return String
     */
    public String getFrontOrderSystemCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderSystemCode(String)--->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode",
            new Class[] {String.class},
            new Object[]{l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode",
            new Class[] {String.class}))
        {
            //2)MockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getFrontOrderSystemCode",
                new Class[] {String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getFrontOrderSystemCode(l_strMarketCode);
    }

    /**
     * (get�Г���������(Mock))<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�Г��������ځv�ݒ�p�������<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCorpCode(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode",
            new Class[] {IfoOrderUnit.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getCorpCode",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getCorpCode",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getCorpCode(l_orderUnit);

    }

    /**
     * (get�i������j�Г���������)<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgCorpCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode",
            new Class[] {IfoOrderUnit.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrgCorpCode",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrgCorpCode",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrgCorpCode(l_orderUnit);
    }
    
    public void lockHostFotypeOrderAll(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "lockHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class},
                new Object[]{l_orderUnit});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "lockHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}))
        {
        	log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImplForMock.lockHostFotypeOrderAll(IfoOrderUnit)");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "lockHostFotypeOrderAll",
                    new Class[] {IfoOrderUnit.class}).asWEB3BaseException();
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "lockHostFotypeOrderAll",
                    new Class[] {IfoOrderUnit.class}).asVoid();
        	return;
        }
        
    	super.lockHostFotypeOrderAll(l_orderUnit);
    }

    /**
     * (get������ؑ�(Mock))<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return ������ؑ�
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderSwitching(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching",
            new Class[] {IfoOrderUnit.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrderSwitching",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (WEB3GentradeOrderSwitching)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrderSwitching",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderSwitching(l_orderUnit);
    }
 
    /**
     * (get���������MQ�f�[�^�R�[�h(mock))<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeCancelMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeCancelMQDataCode(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode",
            new Class[] {IfoOrderUnit.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeCancelMQDataCode",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeCancelMQDataCode",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getChangeCancelMQDataCode(l_orderUnit);
    }

}
@
