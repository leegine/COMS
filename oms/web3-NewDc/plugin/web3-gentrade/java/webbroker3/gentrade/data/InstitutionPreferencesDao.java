head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	InstitutionPreferencesDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link InstitutionPreferencesDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link InstitutionPreferencesRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstitutionPreferencesPK 
 * @@see InstitutionPreferencesRow 
 */
public class InstitutionPreferencesDao extends DataAccessObject {


  /** 
   * ����{@@link InstitutionPreferencesDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private InstitutionPreferencesRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link InstitutionPreferencesRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link InstitutionPreferencesDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link InstitutionPreferencesDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link InstitutionPreferencesRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof InstitutionPreferencesRow )
                return new InstitutionPreferencesDao( (InstitutionPreferencesRow) row );
            throw new java.lang.IllegalArgumentException( "Not a InstitutionPreferencesRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link InstitutionPreferencesRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link InstitutionPreferencesRow}�I�u�W�F�N�g 
    */
    protected InstitutionPreferencesDao( InstitutionPreferencesRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link InstitutionPreferencesRow}�I�u�W�F�N�g���擾���܂��B
   */
    public InstitutionPreferencesRow getInstitutionPreferencesRow() {
        return row;
    }


  /** 
   * �w���{@@link InstitutionPreferencesRow}�I�u�W�F�N�g����{@@link InstitutionPreferencesDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link InstitutionPreferencesRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link InstitutionPreferencesDao}�擾�̂��߂Ɏw���{@@link InstitutionPreferencesRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link InstitutionPreferencesDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static InstitutionPreferencesDao forRow( InstitutionPreferencesRow row ) throws java.lang.IllegalArgumentException {
        return (InstitutionPreferencesDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link InstitutionPreferencesRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link InstitutionPreferencesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link InstitutionPreferencesPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link InstitutionPreferencesParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( InstitutionPreferencesRow.TYPE );
    }


  /** 
   * {@@link InstitutionPreferencesRow}����ӂɓ��肷��{@@link InstitutionPreferencesPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link InstitutionPreferencesRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link InstitutionPreferencesParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link InstitutionPreferencesPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static InstitutionPreferencesPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link InstitutionPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * @@param p_nameSerialNo �����Ώۂł���p_nameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InstitutionPreferencesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InstitutionPreferencesRow findRowByPk( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPreferencesPK pk = new InstitutionPreferencesPK( p_institutionId, p_name, p_nameSerialNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���InstitutionPreferencesPK�I�u�W�F�N�g����{@@link InstitutionPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����InstitutionPreferencesPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link InstitutionPreferencesRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static InstitutionPreferencesRow findRowByPk( InstitutionPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (InstitutionPreferencesRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String,int)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static InstitutionPreferencesDao findDaoByPk( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPreferencesPK pk = new InstitutionPreferencesPK( p_institutionId, p_name, p_nameSerialNo );
        InstitutionPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(InstitutionPreferencesPK)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static InstitutionPreferencesDao findDaoByPk( InstitutionPreferencesPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        InstitutionPreferencesRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link InstitutionPreferencesDao}�ɕR�t��{@@link InstitutionPreferencesRow}���ŊO���L�[�̊֌W������{@@link InstitutionRow}���������܂��B 
   * 
   * @@return {@@link InstitutionPreferencesDao}�ƊO���L�[�̊֌W�ɂ���{@@link InstitutionRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchInstitutionRowViaInstitutionId()}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByInstitutionId(InstitutionRow)}���g�p���Ă��������B 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}�ƊO���L�[�̊֌W�ɂ���{@@link InstitutionPreferencesRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link InstitutionRow}�I�u�W�F�N�g 
   * @@return �w���{@@link InstitutionRow}�ɊO���L�[������{@@link InstitutionPreferencesRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}�ƊO���L�[�̊֌W�ɂ���{@@link InstitutionPreferencesRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link InstitutionPK}�I�u�W�F�N�g 
   * @@return {@@link InstitutionPK}�ƊO���L�[����v����l������{@@link InstitutionPreferencesRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link InstitutionPreferencesRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link InstitutionPreferencesRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            InstitutionPreferencesRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByInstitutionId(InstitutionRow)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(InstitutionRow)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(InstitutionPK)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(long)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionId, p_name, p_nameSerialNo, and �ɂĎw��̒l�����ӂ�{@@link InstitutionPreferencesRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * @@param p_nameSerialNo �����Ώۂł���p_nameSerialNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionId, p_name, p_nameSerialNo, and �̒l�ƈ�v����{@@link InstitutionPreferencesRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static InstitutionPreferencesRow findRowByInstitutionIdNameNameSerialNo( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            InstitutionPreferencesRow.TYPE,
            "institution_id=? and name=? and name_serial_no=?",
            null,
            new Object[] { new Long(p_institutionId), p_name, new Integer(p_nameSerialNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (InstitutionPreferencesRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query InstitutionPreferencesDao.findRowsByInstitutionIdNameNameSerialNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionIdNameNameSerialNo(long, String, int)}�����{@@link #forRow(InstitutionPreferencesRow)}���g�p���Ă��������B 
   */
    public static InstitutionPreferencesDao findDaoByInstitutionIdNameNameSerialNo( long p_institutionId, String p_name, int p_nameSerialNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionIdNameNameSerialNo( p_institutionId, p_name, p_nameSerialNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
