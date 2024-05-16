namespace ExternalDependencies;

public interface IProvideAuditoriumLayouts
{
    AuditoriumDto FindByShowId(string showId);
}